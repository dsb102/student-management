package com.example.quanlisinhvien.serivce.common;

import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.repository.UserRepository;
import com.example.quanlisinhvien.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Common {

    public static Pageable getPageable(Integer page, Integer size, String sort) {
        page = page < 0 ? 0 : page;
        Sort sortable = Sort.by("id").ascending();
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        return PageRequest.of(page, size, sortable);
    }
}
