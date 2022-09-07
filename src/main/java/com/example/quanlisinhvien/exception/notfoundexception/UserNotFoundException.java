package com.example.quanlisinhvien.exception.notfoundexception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super(String.format("Không tìm thấy user có id = %s", id));
    }
}
