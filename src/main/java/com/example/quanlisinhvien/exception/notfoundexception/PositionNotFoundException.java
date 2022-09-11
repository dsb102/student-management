package com.example.quanlisinhvien.exception.notfoundexception;

public class PositionNotFoundException extends RuntimeException{
    public PositionNotFoundException(Long id) {
        super(String.format("Không tìm thấy vị trí có id = %s", id));
    }
}
