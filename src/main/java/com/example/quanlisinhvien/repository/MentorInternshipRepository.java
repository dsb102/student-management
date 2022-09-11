package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.MentorInternship;
import com.example.quanlisinhvien.model.embeddable.MentorInternshipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorInternshipRepository extends JpaRepository<MentorInternship, MentorInternshipKey> {
    List<MentorInternship> findAllByIdMentorId(Long id);
}
