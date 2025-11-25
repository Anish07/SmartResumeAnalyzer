import { Component, inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { ThemeService } from '../../services/theme.service';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [MatIconModule, RouterLink],
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  themeService = inject(ThemeService);
}
