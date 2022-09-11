package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.exception.notfoundexception.PositionNotFoundException;
import com.example.quanlisinhvien.model.Position;
import com.example.quanlisinhvien.repository.PositionRepository;
import com.example.quanlisinhvien.security.AdminAuthorization;
import com.example.quanlisinhvien.serivce.IPositionService;
import com.example.quanlisinhvien.serivce.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
@Slf4j
public class PositionService implements IPositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Page<Position> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return positionRepository.findAll(pageable);
    }

    @Override
    public Position findById(Long id) {
        return positionRepository.findById(id).orElseThrow(() -> {
            throw new PositionNotFoundException(id);
        });
    }

    @AdminAuthorization
    @Override
    public Position create(Position position) {
        return positionRepository.save(position);
    }

    @AdminAuthorization
    @Override
    public Position update(Position position) {
        return positionRepository.save(position);
    }

    @AdminAuthorization
    @Override
    public void deleteById(Long id) {
        Position position = findById(id);
        if (position == null) throw new PositionNotFoundException(id);
        else {
            position.setDelFlg(true);
            positionRepository.save(position);
        }
    }

    @Override
    public Page<Position> findByName(Integer page, Integer size, String sort, String keyword) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return positionRepository.findPositionsByNameStartingWithAndIsDelFlgFalse(pageable, keyword);
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAllByIsDelFlgFalse();
    }
}
