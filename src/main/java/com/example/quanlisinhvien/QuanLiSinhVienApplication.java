package com.example.quanlisinhvien;

import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.eenum.Role;
import com.example.quanlisinhvien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuanLiSinhVienApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanLiSinhVienApplication.class, args);
    }

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setDelFlg(false);
//        user.setRole(Role.ADMIN);
//        userRepository.save(user);
//        System.out.println(user);
//    }
}
