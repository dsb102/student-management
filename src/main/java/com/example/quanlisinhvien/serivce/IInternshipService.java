package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.dto.InternshipDTO;
import com.example.quanlisinhvien.model.Internship;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IInternshipService extends IService<Internship>{
    InternshipDTO create(InternshipDTO internshipDTO);

    InternshipDTO update(InternshipDTO internshipDTO);

    Page<Internship> findByName(Integer page, Integer size, String sort, Long keyword);

    InternshipDTO getInternshipDTOById(Long id);

    List<Internship> getAll();

    List<Internship> getAllByMentorId(Long id);
}
