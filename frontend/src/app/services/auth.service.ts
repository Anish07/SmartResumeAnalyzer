import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private tokenKey = 'auth_token';
  private currentUserSubject = new BehaviorSubject<string | null>(this.getToken());

  private userKey = 'auth_user';

  constructor(private http: HttpClient, private router: Router) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user).pipe(
      tap((res: any) => this.handleAuth(res.token, res.username))
    );
  }

  login(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, user).pipe(
      tap((res: any) => this.handleAuth(res.token, res.username))
    );
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userKey);
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getUsername(): string | null {
    return localStorage.getItem(this.userKey);
  }

  isLoggedIn(): Observable<boolean> {
    return new Observable(observer => {
      this.currentUserSubject.subscribe(token => {
        observer.next(!!token);
      });
    });
  }

  private handleAuth(token: string, username: string) {
    localStorage.setItem(this.tokenKey, token);
    localStorage.setItem(this.userKey, username);
    this.currentUserSubject.next(token);
  }
}
