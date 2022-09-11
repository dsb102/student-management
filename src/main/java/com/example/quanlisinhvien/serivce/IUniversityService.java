package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.University;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUniversityService extends IService<University> {
    Page<University> findByName(Integer page, Integer size, String sort, String keyword);

    List<University> getAll();
}
