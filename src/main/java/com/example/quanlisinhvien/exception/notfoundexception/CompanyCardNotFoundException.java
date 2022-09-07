package com.example.quanlisinhvien.exception.notfoundexception;

public class CompanyCardNotFoundException extends RuntimeException{
    public CompanyCardNotFoundException(Long id) {
        super(String.format("Không tìm thấy thẻ công ty có id = %s", id));
    }
}
