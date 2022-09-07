package com.example.quanlisinhvien.controller;


import com.example.quanlisinhvien.exception.notfoundexception.CompanyCardNotFoundException;
import com.example.quanlisinhvien.model.CompanyCard;
import com.example.quanlisinhvien.serivce.impl.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CompanyCardController {
    private static final String PAGE = "0"; // default page
    private static final String SIZE = "5"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private CompanyCardService companyCardService;

    @GetMapping({"/managerCompanyCard", "/searchCompanyCard"})
    public String managerCompanyCard(Model model,
                                  @RequestParam(name = "keyword", required = false) String keyword,
                                  @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
                                  @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
                                  @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort) {
        if (keyword != null) {
            if (keyword.equals("")) keyword = "1"; // default
            Page<CompanyCard> companyCards = companyCardService.findByName(Integer.parseInt(page), Integer.parseInt(size), sort, Long.parseLong(keyword));
            model.addAttribute("companyCards", companyCards);
            model.addAttribute("keyword", keyword);
        } else {
            Page<CompanyCard> companyCards = companyCardService.getAll(Integer.parseInt(page), Integer.parseInt(size), sort);
            model.addAttribute("companyCards", companyCards);
        }
        return "admin/managerCompanyCard";
    }

    @GetMapping("/editCompanyCard")
    public String editCompanyCard(Model model, @RequestParam("id") Long id) {
        CompanyCard companyCard = companyCardService.findById(id);
        if (companyCard == null) throw new CompanyCardNotFoundException(id);
        else {
            model.addAttribute("companyCard", companyCard);
        }
        return "/admin/editCompanyCard";
    }

    @PostMapping("/editCompanyCard")
    public String saveEditedCompanyCard(@ModelAttribute("companyCard") CompanyCard companyCard, Model model) {
        companyCardService.update(companyCard);
        return "redirect:/admin/managerCompanyCard";
    }

    @GetMapping("/deleteCompanyCard")
    public String deleteCompanyCard(@RequestParam("id") Long id) {
        companyCardService.deleteById(id);
        return "redirect:/admin/managerCompanyCard";
    }

    @GetMapping("/createCompanyCard")
    public String createCompanyCard(@ModelAttribute("CompanyCard") CompanyCard companyCard, Model model) {
        model.addAttribute("CompanyCard", companyCard);
        return "admin/createCompanyCard";
    }

    @PostMapping("/createCompanyCard")
    public String saveCreatedCompanyCard(@Valid CompanyCard companyCard) {
        companyCardService.create(companyCard);
        return "redirect:/admin/managerCompanyCard";
    }
}
