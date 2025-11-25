package com.smartresume.backend.repository;

import com.smartresume.backend.model.Resume;
import com.smartresume.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUserOrderByCreatedAtDesc(User user);
}
