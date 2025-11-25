import { Component, AfterViewInit, ElementRef, ViewChildren, QueryList } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink, MatButtonModule, MatIconModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements AfterViewInit {
  @ViewChildren('animate') animatedElements!: QueryList<ElementRef>;

  ngAfterViewInit() {
    const observer = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.add('visible');
        }
      });
    }, { threshold: 0.1 });

    this.animatedElements.forEach(el => observer.observe(el.nativeElement));
  }
}
