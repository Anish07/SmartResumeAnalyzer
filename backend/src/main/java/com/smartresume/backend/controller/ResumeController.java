package com.smartresume.backend.controller;

import org.apache.tika.exception.TikaException;
import com.smartresume.backend.model.Resume;
import com.smartresume.backend.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeResume(@RequestParam("file") MultipartFile file,
            Principal principal) {
        try {
            return ResponseEntity.ok(resumeService.analyze(file, principal.getName()));
        } catch (IOException | TikaException e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to parse file: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Resume>> getUserResumes(Principal principal) {
        return ResponseEntity.ok(resumeService.getUserResumes(principal.getName()));
    }
}
