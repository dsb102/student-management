package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.CompanyCard;
import com.example.quanlisinhvien.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyCardRepository extends JpaRepository<CompanyCard, Long> {
    Page<CompanyCard> findCompanyCardsById(Pageable pageable, Long keyword);

    List<CompanyCard> findAllByUsingFlgIsFalse();
}
