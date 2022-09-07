package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.dto.MentorDTO;
import com.example.quanlisinhvien.model.Mentor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMentorService extends IService<Mentor>{
    MentorDTO update(MentorDTO mentorDTO);

    MentorDTO create(MentorDTO mentorDTO);

    Page<Mentor> getAll(Integer page, Integer size, String sort);

    List<MentorDTO> getAllDTO();

    MentorDTO getMentorDTOById(Long id);

    List<Mentor> getAll();

    Mentor getMentorById(Long id);
}
