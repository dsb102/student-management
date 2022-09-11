package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.model.*;
import com.example.quanlisinhvien.serivce.impl.InternshipService;
import com.example.quanlisinhvien.serivce.impl.InternshipTimesheetService;
import com.example.quanlisinhvien.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ManagerInternshipTimeSheetController {
    private static final String PAGE = "0"; // default page
    private static final String SIZE = "5"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private InternshipTimesheetService internshipTimesheetService;

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipService internshipService;

    @GetMapping({"/managerInternshipTimesheet", "searchInternshipTimesheet"})
    public String managerInternshipTimesheet(
            Model model,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
            @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
            @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort) {
        if (keyword == null || keyword.equals("")) {
            List<InternshipTimesheet> internshipTimesheets = internshipTimesheetService.getAll();
            model.addAttribute("internshipTimesheets", internshipTimesheets);
        } else {
            List<InternshipTimesheet> internshipTimesheets = internshipTimesheetService.getAllByFullName(keyword);
            model.addAttribute("internshipTimesheets", internshipTimesheets);
        }
        return "admin/managerInternshipTimesheet";
    }

    @GetMapping("/createInternshipTimesheet")
    public String create(@ModelAttribute("internshipTimesheet") InternshipTimesheet internshipTimesheet, Model model) {
        model.addAttribute("internshipTimesheet", internshipTimesheet);
        List<Internship> internships = internshipService.getAll();
        model.addAttribute("internships", internships);
        return "admin/createInternshipTimesheet";
    }

    @PostMapping("/createInternshipTimesheet")
    public String save(@Valid InternshipTimesheet internshipTimesheet) {
        internshipTimesheetService.create(internshipTimesheet);
        return "redirect:/admin/managerInternshipTimesheet";
    }

    @GetMapping("/deleteInternshipTimesheet")
    public String delete(@RequestParam("id") Long id) {
        internshipTimesheetService.deleteById(id);
        return "redirect:/admin/managerInternshipTimesheet";
    }
}
