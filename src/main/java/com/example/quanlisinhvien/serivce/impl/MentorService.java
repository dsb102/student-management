package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.model.dto.MentorDTO;
import com.example.quanlisinhvien.exception.notfoundexception.UserNotFoundException;
import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.eenum.Role;
import com.example.quanlisinhvien.repository.MentorRepository;
import com.example.quanlisinhvien.repository.UserRepository;
import com.example.quanlisinhvien.serivce.IMentorService;
import com.example.quanlisinhvien.serivce.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class MentorService implements IMentorService {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Mentor> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return mentorRepository.findAll(pageable);
    }

    @Override
    public List<MentorDTO> getAllDTO() {
        List<MentorDTO> mentorDTOS = new ArrayList<>();
        List<Mentor> mentors = mentorRepository.findAll();
        mentors.forEach(mentor -> {
            User user = userRepository.findUserByUserId(mentor.getMentorId());
            MentorDTO mentorDTO = MentorDTO.builder()
                    .id(mentor.getMentorId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .isDelFlg(user.isDelFlg())
                    .fullname(user.getFullName())
                    .maxInternship(mentor.getMaxInternship())
                    .phoneNumber(user.getPhoneNumber())
                    .maxInternship(mentor.getMaxInternship())
                    .role(user.getRole())
                    .build();
            mentorDTOS.add(mentorDTO);
        });
        return mentorDTOS;
    }

    @Override
    public Mentor findById(Long id) {
        return mentorRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(id);
        });
    }

    @Override
    public Mentor create(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

//    @AdminAuthorization
    @Override
    public void deleteById(Long id) {
        Mentor mentor = findById(id);
        if (mentor == null) log.warn(String.format("Không tồn tại mentor có id = %s", id));
        else mentorRepository.deleteById(id);
    }

//    @AdminAuthorization
    @Override
    public Mentor update(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Transactional(rollbackOn = {Throwable.class})
    @Override
    public MentorDTO create(MentorDTO mentorDTO) {

        User user = User.builder()
                .userId(mentorDTO.getId())
                .username(mentorDTO.getUsername())
                .password(passwordEncoder.encode(mentorDTO.getPassword()))
                .email(mentorDTO.getEmail())
                .fullName(mentorDTO.getFullname())
                .phoneNumber(mentorDTO.getPhoneNumber())
                .role(Role.ROLE_MENTOR)
                .isDelFlg(false)
                .build();
        Mentor mentor = Mentor.builder()
                .mentorId(user.getUserId())
                .maxInternship(mentorDTO.getMaxInternship())
                .build();
        user.setMentor(mentor);
        mentor.setUser(user);
        mentorRepository.save(mentor);
        userRepository.save(user);
        return mentorDTO;
    }

    @Override
    public MentorDTO getMentorDTOById(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(id);
        });
        User user = userRepository.findUserByUserId(id);
        if (user == null) throw new UserNotFoundException(id);
        MentorDTO mentorDTO = MentorDTO.builder()
                .id(id)
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullname(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .maxInternship(mentor.getMaxInternship())
                .isDelFlg(user.isDelFlg())
//                .skype(user.getSkype())
                .role(user.getRole())
//                .mentorId(mentor.getMentorId())
                .build();
        return mentorDTO;
    }

    @Override
    public List<Mentor> getAll() {
        return mentorRepository.getAllByUserIsDelFlgFalse();
    }

    @Override
    public Mentor getMentorById(Long id) {
        return mentorRepository.findMentorByMentorId(id);
    }

    @Transactional(rollbackOn = {Throwable.class})
    @Override
    public MentorDTO update(MentorDTO mentorDTO) {
        User oldUser = userRepository.findUserByUserId(mentorDTO.getId());
        oldUser.setUsername(mentorDTO.getUsername());
//        oldUser.setPassword(mentorDTO.getPassword());
        oldUser.setEmail(mentorDTO.getEmail());
        oldUser.setFullName(mentorDTO.getFullname());
        userRepository.save(oldUser);

        Mentor oldMentor = mentorRepository.findMentorByMentorId(mentorDTO.getId());
        mentorRepository.save(oldMentor);
        return mentorDTO;
    }
}
