package com.example.quanlisinhvien.exception.notfoundexception;

public class InternshipTimesheetNotFoundException extends RuntimeException{
    public InternshipTimesheetNotFoundException(Long id) {
        super(String.format("Không tìm thấy bảng thời gian làm việc của svtt có id = %d", id));
    }
}
