package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.eenum.Role;

import java.util.List;

public interface IUserService extends IService<User> {
    User findById(Long id);

    List<User> getAll();

    User findUserByUsername(String username);

    List<User> getAllByRole(Role role);

    User getCurrentUserLogin();

    User findByEmail(String email);
}
