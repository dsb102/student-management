package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.Internship;
import com.example.quanlisinhvien.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    Page<Internship> findInternshipsByInternshipId(Pageable pageable, Long id);

    Internship findInternshipByInternshipId(Long id);

    List<Internship> findAllByUserIsDelFlgFalse();

    List<Internship> findInternshipsByMentorIdAndUser_IsDelFlgFalse(Long id);
}