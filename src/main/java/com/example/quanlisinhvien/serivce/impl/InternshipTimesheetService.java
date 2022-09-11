package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.exception.notfoundexception.InternshipTimesheetNotFoundException;
import com.example.quanlisinhvien.model.Internship;
import com.example.quanlisinhvien.model.InternshipTimesheet;
import com.example.quanlisinhvien.repository.InternshipTimesheetRepository;
import com.example.quanlisinhvien.serivce.IInternshipTimesheetService;
import com.example.quanlisinhvien.serivce.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InternshipTimesheetService implements IInternshipTimesheetService {
    @Autowired
    private InternshipTimesheetRepository internshipTimesheetRepository;

    @Override
    public Page<InternshipTimesheet> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return internshipTimesheetRepository.findAll(pageable);
    }

    @Override
    public InternshipTimesheet findById(Long id) {
        return internshipTimesheetRepository.findById(id).orElseThrow(() -> {
            throw new InternshipTimesheetNotFoundException(id);
        });
    }

    @Override
    public InternshipTimesheet create(InternshipTimesheet internshipTimesheet) {
        return internshipTimesheetRepository.save(internshipTimesheet);
    }

    @Override
    public InternshipTimesheet update(InternshipTimesheet internshipTimesheet) {
        return internshipTimesheetRepository.save(internshipTimesheet);
    }

    @Override
    public void deleteById(Long id) {
        InternshipTimesheet internshipTimesheet = findById(id);
        if (internshipTimesheet == null) throw new InternshipTimesheetNotFoundException(id);
        else {
            internshipTimesheet.setDelFlg(true);
            internshipTimesheetRepository.save(internshipTimesheet);
        }
    }

    @Override
    public List<InternshipTimesheet> getAll() {
        return internshipTimesheetRepository.getAllByIsDelFlgFalse();
    }

    @Override
    public List<InternshipTimesheet> getAllByFullName(String name) {
        return internshipTimesheetRepository.getAllByInternship_User_FullNameStartsWith(name);
    }

    @Override
    public List<InternshipTimesheet> getAllById(Long id) {
        return internshipTimesheetRepository.getAllByIsDelFlgFalseAndAndInternship_InternshipId(id);
    }
}
