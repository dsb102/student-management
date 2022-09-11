package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Page<Mentor> findAll(Pageable pageable);

    Mentor findMentorByMentorId(Long id);

    List<Mentor> getAllByUserIsDelFlgFalse();
}
