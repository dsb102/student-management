package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.exception.notfoundexception.CompanyCardNotFoundException;
import com.example.quanlisinhvien.model.CompanyCard;
import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.University;
import com.example.quanlisinhvien.repository.CompanyCardRepository;
import com.example.quanlisinhvien.serivce.ICompanyCardService;
import com.example.quanlisinhvien.serivce.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCardService implements ICompanyCardService {
    @Autowired
    private CompanyCardRepository companyCardRepository;

    @Override
    public Page<CompanyCard> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return companyCardRepository.findAll(pageable);
    }

    @Override
    public CompanyCard findById(Long id) {
        return companyCardRepository.findById(id).orElseThrow(() -> {
            throw new CompanyCardNotFoundException(id);
        });
    }

    @Override
    public CompanyCard create(CompanyCard companyCard) {
        return companyCardRepository.save(companyCard);
    }

    @Override
    public CompanyCard update(CompanyCard companyCard) {
        return companyCardRepository.save(companyCard);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<CompanyCard> findByName(Integer page, Integer size, String sort, Long keyword) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return companyCardRepository.findCompanyCardsById(pageable, keyword);
    }

    @Override
    public List<CompanyCard> getAllAvailable() {
        return companyCardRepository.findAllByUsingFlgIsFalse();
    }
}
