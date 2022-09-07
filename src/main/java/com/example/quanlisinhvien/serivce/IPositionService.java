package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.Position;
import com.example.quanlisinhvien.model.University;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPositionService extends IService<Position>{
    Page<Position> findByName(Integer page, Integer size, String sort, String keyword);

    List<Position> getAll();
}
