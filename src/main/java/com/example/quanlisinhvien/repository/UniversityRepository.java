package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    Page<University> findUniversitiesByNameStartingWith(Pageable pageable, String keyword);
}
