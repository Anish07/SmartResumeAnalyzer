import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  private apiUrl = 'http://localhost:8080/api/resume';

  constructor(private http: HttpClient) { }

  analyzeResume(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<any>(`${this.apiUrl}/analyze`, formData);
  }

  getResumes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
