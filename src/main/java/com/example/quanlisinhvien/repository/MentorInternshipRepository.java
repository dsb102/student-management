package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.MentorInternship;
import com.example.quanlisinhvien.model.embeddable.MentorInternshipKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorInternshipRepository extends JpaRepository<MentorInternship, MentorInternshipKey> {
    List<MentorInternship> findAllByIdMentorId(Long id);
}
