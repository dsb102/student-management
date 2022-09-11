package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.model.Internship;
import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.MentorInternship;
import com.example.quanlisinhvien.model.UserPosition;
import com.example.quanlisinhvien.repository.*;
import com.example.quanlisinhvien.serivce.IMentorInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class MentorInternshipService implements IMentorInternshipService {

    @Autowired
    private MentorInternshipRepository mentorInternshipRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private UserPositionRepository userPositionRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Transactional(rollbackOn = {Throwable.class})
    @Override
    public MentorInternship assignInternship(MentorInternship mentorInternship) {
        mentorInternship.setCreateAt(LocalDateTime.now());
        mentorInternshipRepository.save(mentorInternship);
        Mentor mentor = mentorInternship.getMentor();
        Internship internship = mentorInternship.getInternship();
        UserPosition mentorPosition = new UserPosition();
        UserPosition internshipPosition = new UserPosition();
        mentorPosition.setPosition(positionRepository.findPositionById(mentorInternship.getPositionId()));
        internshipPosition.setPosition(positionRepository.findPositionById(mentorInternship.getPositionId()));
        internshipPosition.setUser(internship.getUser());
        mentorPosition.setUser(mentor.getUser());
        userPositionRepository.save(internshipPosition);
        userPositionRepository.save(mentorPosition);
        internship.setMentorId(mentor.getMentorId());
        internshipRepository.save(internship);
        return mentorInternship;
    }

    @Override
    public int getNumberOfInternshipCurrent(Long id) {
        return mentorInternshipRepository.findAllByIdMentorId(id).size();
    }
}
