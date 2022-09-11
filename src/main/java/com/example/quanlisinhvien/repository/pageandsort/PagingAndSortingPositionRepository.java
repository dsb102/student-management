package com.example.quanlisinhvien.repository.pageandsort;

import com.example.quanlisinhvien.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingAndSortingPositionRepository extends CrudRepository<Position, Long> {
    Page<Position> findByNameContaining(String name, Pageable pageable);

    Page<Position> findAll(Pageable pageable);
}
