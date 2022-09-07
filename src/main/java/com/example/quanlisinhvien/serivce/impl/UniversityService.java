package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.exception.notfoundexception.UniversityNotFoundException;
import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.University;
import com.example.quanlisinhvien.repository.UniversityRepository;
import com.example.quanlisinhvien.serivce.IUniversityService;
import com.example.quanlisinhvien.serivce.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class UniversityService implements IUniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public Page<University> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return universityRepository.findAll(pageable);
    }

    @Override
    public University findById(Long id) {
        return universityRepository.findById(id).orElseThrow(() -> {
            throw new UniversityNotFoundException(id);
        });
    }

    @Override
    public University create(University university) {
//        university.setCreateId();
        university.setCreateAt(LocalDateTime.now());
        return universityRepository.save(university);
    }

    @Override
    public University update(University university) {
//        university.setModifiedId();
        university.setModifiedAt(LocalDateTime.now());
        return universityRepository.save(university);
    }

    @Override
    public void deleteById(Long id) {
        University university = findById(id);
        if (university == null) throw new UniversityNotFoundException(id);
        else {
            university.setDelFlg(true);
            universityRepository.save(university);
        }
    }

    @Override
    public Page<University> findByName(Integer page, Integer size, String sort, String keyword) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return universityRepository.findUniversitiesByNameStartingWith(pageable, keyword);
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }
}
