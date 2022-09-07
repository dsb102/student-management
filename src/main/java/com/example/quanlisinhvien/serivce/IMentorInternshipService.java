package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.MentorInternship;

public interface IMentorInternshipService {
    MentorInternship assignInternship(MentorInternship mentorInternship);

    int getNumberOfInternshipCurrent(Long id);
}
