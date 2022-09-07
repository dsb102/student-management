package com.example.quanlisinhvien.serivce;

import org.springframework.data.domain.Page;

public interface IService<T> {
    Page<T> getAll(Integer page, Integer size, String sort);

    T findById(Long id);

    T create(T t);

    T update(T t);

    void deleteById(Long id);
}
