package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.InternshipTimesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipTimesheetRepository extends JpaRepository<InternshipTimesheet, Long> {
    List<InternshipTimesheet> getAllByInternship_User_FullNameStartsWith(String fullName);

    List<InternshipTimesheet> getAllByIsDelFlgFalse();

    List<InternshipTimesheet> getAllByIsDelFlgFalseAndAndInternship_InternshipId(Long id);
}
