package com.example.quanlisinhvien.exception.notfoundexception;

public class UniversityNotFoundException extends RuntimeException{
    public UniversityNotFoundException(Long id) {
        super(String.format("Không tìm thấy trường đại học có id = %s", id));
    }
}
