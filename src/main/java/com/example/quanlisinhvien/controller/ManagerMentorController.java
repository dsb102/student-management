package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.MentorInternship;
import com.example.quanlisinhvien.serivce.impl.*;
import com.example.quanlisinhvien.model.dto.MentorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class ManagerMentorController {

    private static final String PAGE = "0"; // default page
    private static final String SIZE = "5"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private MentorInternshipService mentorInternshipService;

    @GetMapping({"managerMentor", "searchMentor"})
    public String managerMentor(Model model,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
                                @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
                                @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort,
                                @ModelAttribute("mentorInternship") MentorInternship mentorInternship
                                ) {
        model.addAttribute("mentorAssigns", mentorService.getAll());
        model.addAttribute("internshipAssigns", internshipService.getAll());
        model.addAttribute("positions", positionService.getAll());
        List<MentorDTO> mentorDTOList = new ArrayList<>();
        if (keyword == null || keyword.equals("")) {
            mentorDTOList = mentorService.getAllDTO();
        } else {
            MentorDTO mentorDTO = mentorService.getMentorDTOById(Long.parseLong(keyword));
            if (mentorDTO == null) return "404";
            mentorDTOList.add(mentorDTO);
        }
        model.addAttribute("mentors", mentorDTOList);
        return "admin/managerMentor";
    }

    @GetMapping("/createMentor")
    public String create(@ModelAttribute("mentor") MentorDTO mentorDTO, Model model) {
        model.addAttribute("mentorDTO", mentorDTO);
        return "admin/createMentor";
    }

    @PostMapping("/createMentor")
    public String save(@Valid MentorDTO mentorDTO) {
        mentorService.create(mentorDTO);
        return "redirect:/admin/managerMentor";
    }

    @GetMapping("/deleteMentor")
    public String deleteMentor(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/managerMentor";
    }

    @PostMapping("/assginInternship")
    public String assignInternship(@Valid MentorInternship mentorInternship, Model model) {
        Mentor mentor = mentorInternship.getMentor();
        int currentNumberInternship = mentorInternshipService.getNumberOfInternshipCurrent(mentor.getMentorId());
        if (currentNumberInternship >= mentor.getMaxInternship()) {
            log.warn("Đã đủ số lượng internship của mentor này");
        } else
            mentorInternshipService.assignInternship(mentorInternship);
        return "redirect:/admin/managerMentor";
    }

    @GetMapping("/editMentor")
    public String editInternship(Model model, @RequestParam("id") Long id) {
        MentorDTO mentorDTO = mentorService.getMentorDTOById(id);
        model.addAttribute("mentorDTO", mentorDTO);
        return "/admin/editMentor";
    }

    @PostMapping("/editMentor")
    public String saveEditedInternship(@ModelAttribute("mentorDTO") MentorDTO mentorDTO, Model model) {
        mentorService.update(mentorDTO);
        return "redirect:/admin/managerMentor";
    }
}
