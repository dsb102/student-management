package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.model.dto.InternshipDTO;
import com.example.quanlisinhvien.exception.notfoundexception.UserNotFoundException;
import com.example.quanlisinhvien.model.CompanyCard;
import com.example.quanlisinhvien.model.Internship;
import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.eenum.Role;
import com.example.quanlisinhvien.repository.InternshipRepository;
import com.example.quanlisinhvien.repository.UserRepository;
import com.example.quanlisinhvien.security.AdminAuthorization;
import com.example.quanlisinhvien.serivce.IInternshipService;
import com.example.quanlisinhvien.serivce.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class InternshipService implements IInternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CompanyCardService companyCardService;

    @Override
    public Page<Internship> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return internshipRepository.findAll(pageable);
    }

    public List<InternshipDTO> getAllDTO() {
        List<InternshipDTO> internshipDTOS = new ArrayList<>();
        List<Internship> internships = internshipRepository.findAllByUserIsDelFlgFalse();
        internships.forEach(internship -> {
            User user = userRepository.findUserByUserId(internship.getInternshipId());
            InternshipDTO internshipDTO = InternshipDTO.builder()
                    .id(internship.getInternshipId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .isDelFlg(user.isDelFlg())
                    .fullname(user.getFullName())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .birthday(internship.getBirthday())
                    .universityId(internship.getUniversity().getId())
//                    .scholastic(internship.getScholastic())
//                    .mentorId(internship.getMentorId())
                    .companyCardId(internship.getCompanyCard().getId())
                    .build();
            internshipDTOS.add(internshipDTO);
        });
        return internshipDTOS;
    }

    @Override
    public Internship findById(Long id) {
        return internshipRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(id);
        });
    }

    @AdminAuthorization
    @Override
    public Internship create(Internship internship) {
        return internshipRepository.save(internship);
    }

    @AdminAuthorization
    @Override
    public void deleteById(Long id) {
        Internship internship = findById(id);
        if (internship == null) log.warn(String.format("Không tồn tại internship có id = %s", id));
        else internshipRepository.deleteById(id);
    }

    @AdminAuthorization
    @Override
    public Internship update(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Transactional(rollbackOn = {Throwable.class})
    @Override
    public InternshipDTO create(InternshipDTO internshipDTO) {

        User user = User.builder()
                .userId(internshipDTO.getId())
                .username(internshipDTO.getUsername())
                .password(passwordEncoder.encode(internshipDTO.getPassword()))
                .email(internshipDTO.getEmail())
                .fullName(internshipDTO.getFullname())
                .phoneNumber(internshipDTO.getPhoneNumber())
                .role(Role.ROLE_INTERNSHIP)
                .isDelFlg(false)
                .build();
        Internship internship = Internship.builder()
                .internshipId(user.getUserId())
                .birthday(internshipDTO.getBirthday())
                .university(universityService.findById(internshipDTO.getUniversityId()))
                .companyCard(companyCardService.findById(internshipDTO.getCompanyCardId()))
                .build();
        user.setInternship(internship);
        internship.setUser(user);
        internshipRepository.save(internship);
        userRepository.save(user);
        CompanyCard companyCard = companyCardService.findById(internshipDTO.getCompanyCardId());
        companyCard.setUsingFlg(true);
        companyCardService.update(companyCard);
        return internshipDTO;
    }

    @Transactional(rollbackOn = {Throwable.class})
    @Override
    public InternshipDTO update(InternshipDTO internshipDTO) {
        User oldUser = userRepository.findUserByUserId(internshipDTO.getId());
        oldUser.setUsername(internshipDTO.getUsername());
//        oldUser.setPassword(internshipDTO.getPassword());
        oldUser.setEmail(internshipDTO.getEmail());
        oldUser.setFullName(internshipDTO.getFullname());
        userRepository.save(oldUser);

        Internship oldInternship = internshipRepository.findInternshipByInternshipId(internshipDTO.getId());
        oldInternship.setBirthday(internshipDTO.getBirthday());
        oldInternship.setUniversity(universityService.findById(internshipDTO.getUniversityId()));
        internshipRepository.save(oldInternship);
        return internshipDTO;
    }

    @Override
    public Page<Internship> findByName(Integer page, Integer size, String sort, Long keyword) {
        Pageable pageable = Common.getPageable(page, size, sort);
        return internshipRepository.findInternshipsByInternshipId(pageable, keyword);
    }

    @Override
    public InternshipDTO getInternshipDTOById(Long id) {
        Internship internship = internshipRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(id);
        });
        User user = userRepository.findUserByUserId(id);
        if (user == null) throw new UserNotFoundException(id);
        InternshipDTO internshipDTO = InternshipDTO.builder()
                .id(id)
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullname(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .isDelFlg(user.isDelFlg())
//                .skype(user.getSkype())
                .role(user.getRole())
                .birthday(internship.getBirthday())
                .universityId(internship.getUniversity().getId())
//                .mentorId(internship.getMentorId())
                .companyCardId(internship.getCompanyCard().getId())
                .build();
        return internshipDTO;
    }

    @Override
    public List<Internship> getAll() {
        return internshipRepository.findAllByUserIsDelFlgFalse();
    }

    @Override
    public List<Internship> getAllByMentorId(Long id) {
        return internshipRepository.findInternshipsByMentorIdAndUser_IsDelFlgFalse(id);
    }
}
