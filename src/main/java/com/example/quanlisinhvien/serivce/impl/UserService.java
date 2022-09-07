package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.config.CustomUserDetails;
import com.example.quanlisinhvien.exception.notfoundexception.UserNotFoundException;
import com.example.quanlisinhvien.model.Internship;
import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.eenum.Role;
import com.example.quanlisinhvien.repository.UserRepository;
import com.example.quanlisinhvien.serivce.IUserService;
import com.example.quanlisinhvien.serivce.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findUserByUserId(id);

    }

    @Override
    public User create(User user) {
        user.setDelFlg(false);
        user.setCreateAt(LocalDateTime.now());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        if (user == null)
            log.warn(String.format("Không tồn tại user có id = %s", id));
        else {
            user.setDelFlg(true);
//            user.getInternship().getCompanyCard().setUsingFlg(false); // không nên sử dụng lại thẻ vì đã gắn quan hệ
            userRepository.save(user);

        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAllByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }

    @Override
    public User getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findUserByUsername(userDetails.getUsername());

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new CustomUserDetails(user);
    }

    public Page<User> findUsersByUsernameStartingWith(Integer page, Integer size, String sort, String keyUsername) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return userRepository.findUsersByUsernameStartingWith(pageable, keyUsername);
    }

    public Page<User> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return userRepository.findAll(pageable);
    }
}
