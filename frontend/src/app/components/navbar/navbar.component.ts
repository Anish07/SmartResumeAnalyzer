import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth.service';
import { ThemeService } from '../../services/theme.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  authService = inject(AuthService);
  themeService = inject(ThemeService);
  isLoggedIn$ = this.authService.isLoggedIn();

  logout() {
    this.authService.logout();
  }

  toggleTheme() {
    this.themeService.toggleTheme();
  }
}
