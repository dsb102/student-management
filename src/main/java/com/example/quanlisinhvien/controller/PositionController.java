package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.exception.notfoundexception.PositionNotFoundException;
import com.example.quanlisinhvien.model.Position;
import com.example.quanlisinhvien.repository.pageandsort.PagingAndSortingPositionRepository;
import com.example.quanlisinhvien.serivce.impl.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PositionController {
    private static final String PAGE = "0"; // default page
    private static final String SIZE = "3"; // default size on page
    private static final String SORT = "ASC"; // default sort Ascending

    @Autowired
    private PositionService positionService;

    @Autowired
    private PagingAndSortingPositionRepository pagingAndSortingPositionRepository;

    @GetMapping({"/managerPosition", "/searchPosition"})
    public String managerPosition(Model model,
                                    @RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "page", required = false, defaultValue = PAGE) String page,
                                    @RequestParam(name = "size", required = false, defaultValue = SIZE) String size,
                                    @RequestParam(name = "sort", required = false, defaultValue = SORT) String sort,
                                    @RequestParam(name = "checkId", required = false, defaultValue = "false") String checkId,
                                    @RequestParam(name = "checkName", required = false, defaultValue = "false") String checkName
                                  ) {
        List<Sort.Order> orders = new ArrayList<>();
        if (checkId.equals("on")) {
            Sort.Direction direId = checkId.equals("on") ?  Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order orderId = new Sort.Order(direId, "id");
            orders.add(orderId);
        }

        if (checkName.equals("on")) {
            Sort.Direction direName = checkName.equals("on") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order orderName = new Sort.Order(direName, "name");
            orders.add(orderName);
        }
        Pageable pagingSort = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(orders));
        Page<Position> positions;
        if (keyword != null) {
            positions = pagingAndSortingPositionRepository.findByNameContaining(keyword, pagingSort);
        } else {
            positions = pagingAndSortingPositionRepository.findAll(pagingSort);
        }
        if (checkId.equals("on")) {
            model.addAttribute("checkId", true);
            model.addAttribute("checkName", false);
        }

        if (checkName.equals("on")) {
            model.addAttribute("checkId", false);
            model.addAttribute("checkName", true);
        }

        System.out.println(checkId + " " + checkName);
        model.addAttribute("positions", positions);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);

        return "admin/managerPosition";
    }

    @GetMapping("/editPosition")
    public String editPosition(Model model, @RequestParam("id") Long id) {
        Position position = positionService.findById(id);
        if (position == null) throw new PositionNotFoundException(id);
        else {
            model.addAttribute("position", position);
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
