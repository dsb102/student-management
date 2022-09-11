package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.Position;
import com.example.quanlisinhvien.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Page<Position> findPositionsByNameStartingWithAndIsDelFlgFalse(Pageable pageable, String keyword);

    List<Position> findAllByIsDelFlgFalse();

    Position findPositionById(Long id);
}
