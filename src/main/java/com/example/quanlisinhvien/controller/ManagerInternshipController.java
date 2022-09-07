package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.model.dto.InternshipDTO;
import com.example.quanlisinhvien.model.CompanyCard;
import com.example.quanlisinhvien.model.University;
import com.example.quanlisinhvien.serivce.impl.CompanyCardService;
import com.example.quanlisinhvien.serivce.impl.InternshipService;
import com.example.quanlisinhvien.serivce.impl.UniversityService;
import com.example.quanlisinhvien.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class ManagerInternshipController {

    private static final String PAGE = "0"; // default page
    private static final String SIZE = "5"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private CompanyCardService companyCardService;

    @Autowired
    private UniversityService universityService;

    @GetMapping({"managerInternship", "searchInternship"})
    public String managerInternship(Model model,
                                    @RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
                                    @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
                                    @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort) {
        if (keyword == null || keyword.equals("")) {
            List<InternshipDTO> internshipDTOList = internshipService.getAllDTO();
            model.addAttribute("internships", internshipDTOList);
        } else {
            List<InternshipDTO> internshipDTOList = new ArrayList<>();
            InternshipDTO internshipDTO = internshipService.getInternshipDTOById(Long.parseLong(keyword));
            if (internshipDTO == null) return "404";
            internshipDTOList.add(internshipDTO);
            model.addAttribute("internships", internshipDTOList);
        }
        return "admin/managerInternship";
    }

    @GetMapping("/createInternship")
    public String create(@ModelAttribute("internship") InternshipDTO internshipDTO, Model model, BindingResult bindingResult) {
        model.addAttribute("internshipDTO", internshipDTO);
        List<University> universities = universityService.getAll();
        model.addAttribute("universities", universities);
        List<CompanyCard> companyCards = companyCardService.getAllAvailable();
        model.addAttribute("companyCards", companyCards);
        return "admin/createInternship";
    }

    @PostMapping("/createInternship")
    public String save(@Valid InternshipDTO internshipDTO, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            internshipService.create(internshipDTO);
            model.addAttribute("messageSuccess", "Đăng ký thành công");
        } else {
            model.addAttribute("messageSuccess", "Đăng ký thất bại");
        }
        List<University> universities = universityService.getAll();
        model.addAttribute("universities", universities);
        List<CompanyCard> companyCards = companyCardService.getAllAvailable();
        model.addAttribute("companyCards", companyCards);
        return "admin/createInternship";
    }

    @GetMapping("deleteInternship")
    public String deleteInternship(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/managerInternship";
    }

    @GetMapping("/editInternship")
    public String editInternship(Model model, @RequestParam("id") Long id) {
        model.addAttribute("universities", universityService.getAll());
        InternshipDTO internshipDTO = internshipService.getInternshipDTOById(id);
        model.addAttribute("internshipDTO", internshipDTO);
        return "/admin/editInternship";
    }

    @PostMapping("/editInternship")
    public String saveEditedInternship(@ModelAttribute("internshipDTO") InternshipDTO internshipDTO, Model model) {
        internshipService.update(internshipDTO);
        return "redirect:/admin/managerInternship";
    }
}
