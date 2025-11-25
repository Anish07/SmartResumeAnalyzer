import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatCheckboxModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  user = { username: '', email: '', password: '' };
  agreedToTerms = false;
  error = '';

  constructor(private authService: AuthService, private router: Router) { }

  register() {
    this.authService.register(this.user).subscribe({
      next: () => this.router.navigate(['/dashboard']),
      error: (err) => this.error = 'Registration failed'
    });
  }
}
