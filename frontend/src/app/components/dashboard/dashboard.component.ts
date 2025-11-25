import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ResumeService } from '../../services/resume.service';
import { AuthService } from '../../services/auth.service';
import { UploadComponent } from '../upload/upload.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MarkdownModule } from 'ngx-markdown';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, UploadComponent, MatCardModule, MatButtonModule, MatIconModule, MarkdownModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  resumes: any[] = [];
  expandedResumeId: number | null = null;
  totalResumes: number = 0;
  averageScore: number = 0;
  username: string = 'User';

  constructor(private resumeService: ResumeService, private authService: AuthService) { }

  ngOnInit() {
    this.loadResumes();
    const storedUser = this.authService.getUsername();
    if (storedUser) {
      this.username = storedUser;
    }
  }

  loadResumes() {
    this.resumeService.getResumes().subscribe(resumes => {
      this.resumes = resumes;
      this.totalResumes = resumes.length;
      // Placeholder for average score logic
      this.averageScore = resumes.length > 0 ? 85 : 0;
    });
  }

  toggleExpand(id: number) {
    if (this.expandedResumeId === id) {
      this.expandedResumeId = null;
    } else {
      this.expandedResumeId = id;
    }
  }

  onUploadComplete() {
    this.loadResumes();
  }
}
