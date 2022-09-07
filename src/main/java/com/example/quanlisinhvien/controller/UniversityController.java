package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.exception.notfoundexception.UniversityNotFoundException;
import com.example.quanlisinhvien.model.University;
import com.example.quanlisinhvien.serivce.impl.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UniversityController {
    private static final String PAGE = "0"; // default page
    private static final String SIZE = "5"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private UniversityService universityService;

    @GetMapping({"/managerUniversity", "/searchUniversity"})
    public String managerUniversity(Model model,
                                    @RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
                                    @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
                                    @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort) {
        if (keyword != null) {
            Page<University> universities = universityService.findByName(Integer.parseInt(page), Integer.parseInt(size), sort, keyword);
            model.addAttribute("universities", universities);
            model.addAttribute("keyword", keyword);
        } else {
            Page<University> universities = universityService.getAll(Integer.parseInt(page), Integer.parseInt(size), sort);
            model.addAttribute("universities", universities);
        }

        return "admin/managerUniversity";
    }

    @GetMapping("/editUniversity")
    public String editUniversity(Model model, @RequestParam("id") Long id) {
        University university = universityService.findById(id);
        if (university == null) throw new UniversityNotFoundException(id);
        else {
            model.addAttribute("university", university);
        }
        return "/admin/editUniversity";
    }

    @PostMapping("/editUniversity")
    public String saveEditedUniversity(@ModelAttribute("university") University university, Model model) {
        universityService.update(university);
        return "redirect:/admin/managerUniversity";
    }

    @GetMapping("/deleteUniversity")
    public String deleteUniversity(@RequestParam("id") Long id) {
        universityService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/createUniversity")
    public String createUniversity(@ModelAttribute("university") University university, Model model) {
        model.addAttribute("user", university);
        return "admin/createUniversity";
    }

    @PostMapping("/createUniversity")
    public String saveCreatedUniversity(@Valid University university) {
        universityService.create(university);
        return "redirect:/admin/managerUniversity";
    }
}
