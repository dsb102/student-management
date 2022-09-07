package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.InternshipTimesheet;

import java.util.List;

public interface IInternshipTimesheetService extends IService<InternshipTimesheet>{
    List<InternshipTimesheet> getAll();

    List<InternshipTimesheet> getAllByFullName(String name);

    List<InternshipTimesheet> getAllById(Long id);
}
