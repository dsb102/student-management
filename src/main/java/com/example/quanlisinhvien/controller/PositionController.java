package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.exception.notfoundexception.PositionNotFoundException;
import com.example.quanlisinhvien.model.Position;
import com.example.quanlisinhvien.serivce.impl.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PositionController {
    private static final String PAGE = "0"; // default page
    private static final String SIZE = "5"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private PositionService positionService;

    @GetMapping({"/managerPosition", "/searchPosition"})
    public String managerPosition(Model model,
                                    @RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
                                    @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
                                    @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort) {
        if (keyword != null) {
            Page<Position> positions = positionService.findByName(Integer.parseInt(page), Integer.parseInt(size), sort, keyword);
            model.addAttribute("positions", positions);
            model.addAttribute("keyword", keyword);
        } else {
            Page<Position> positions = positionService.getAll(Integer.parseInt(page), Integer.parseInt(size), sort);
            model.addAttribute("positions", positions);
        }
        return "admin/managerPosition";
    }

    @GetMapping("/editPosition")
    public String editPosition(Model model, @RequestParam("id") Long id) {
        Position position = positionService.findById(id);
        if (position == null) throw new PositionNotFoundException(id);
        else {
            model.addAttribute("Position", position);
        }
        return "/admin/editPosition";
    }

    @PostMapping("/editPosition")
    public String saveEditedPosition(@ModelAttribute("position") Position position, Model model) {
        positionService.update(position);
        return "redirect:/admin/managerPosition";
    }

    @GetMapping("/deletePosition")
    public String deletePosition(@RequestParam("id") Long id) {
        positionService.deleteById(id);
        return "redirect:/admin/managerPosition";
    }

    @GetMapping("/createPosition")
    public String createPosition(@ModelAttribute("position") Position position, Model model) {
        model.addAttribute("position", position);
        return "admin/createPosition";
    }

    @PostMapping("/createPosition")
    public String saveCreatedPosition(@Valid Position position) {
        positionService.create(position);
        return "redirect:/admin/managerPosition";
    }
}
