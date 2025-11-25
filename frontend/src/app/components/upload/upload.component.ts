import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { ResumeService } from '../../services/resume.service';

import { MarkdownModule } from 'ngx-markdown';

@Component({
  selector: 'app-upload',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatCardModule, MatProgressBarModule, MarkdownModule],
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent {
  selectedFile: File | null = null;
  analysisResult: any = null;
  isLoading = false;
  @Output() uploadComplete = new EventEmitter<void>();

  constructor(private resumeService: ResumeService) { }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  upload() {
    if (!this.selectedFile) return;

    this.isLoading = true;
    this.resumeService.analyzeResume(this.selectedFile).subscribe({
      next: (res) => {
        this.analysisResult = res;
        this.isLoading = false;
        this.uploadComplete.emit();
      },
      error: (err) => {
        console.error(err);
        this.isLoading = false;
      }
    });
  }
}
