package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.eenum.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByUserId(Long id);

    Page<User> findUsersByUsernameStartingWith(Pageable pageable, String keyUsername);

    List<User> findUsersByRole(Role role);

    User findUserByEmail(String email);
}
