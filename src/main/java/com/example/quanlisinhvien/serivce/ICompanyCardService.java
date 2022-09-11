package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.CompanyCard;
import com.example.quanlisinhvien.model.University;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICompanyCardService extends IService<CompanyCard>{
    Page<CompanyCard> findByName(Integer page, Integer size, String sort, Long keyword);

    List<CompanyCard> getAllAvailable();
}
