package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findAllByInternship_InternshipIdAndIsDelFlgFalse(Long id);

    List<Reviews> findAllByMentor_MentorIdAndIsDelFlgFalse(Long id);
}
