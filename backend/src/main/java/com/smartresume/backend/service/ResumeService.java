package com.smartresume.backend.service;

import org.apache.tika.exception.TikaException;
import com.smartresume.backend.model.Resume;
import com.smartresume.backend.model.User;
import com.smartresume.backend.repository.ResumeRepository;
import com.smartresume.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final TikaParserService tikaParserService;
    private final AiAnalysisService aiAnalysisService;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public Map<String, Object> analyze(MultipartFile file, String username) throws IOException, TikaException {
        String text = tikaParserService.parse(file);
        String analysis = aiAnalysisService.analyzeResume(text);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resume resume = new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setContent(text);
        resume.setAnalysisResult(analysis);
        resume.setUser(user);
        resumeRepository.save(resume);

        return Map.of(
                "text", text,
                "analysis", analysis);
    }

    public List<Resume> getUserResumes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return resumeRepository.findByUserOrderByCreatedAtDesc(user);
    }
}
