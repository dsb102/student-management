package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.model.Internship;
import com.example.quanlisinhvien.model.InternshipTimesheet;
import com.example.quanlisinhvien.model.Reviews;
import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.serivce.common.Common;
import com.example.quanlisinhvien.serivce.impl.InternshipTimesheetService;
import com.example.quanlisinhvien.serivce.impl.MentorService;
import com.example.quanlisinhvien.serivce.impl.ReviewsService;
import com.example.quanlisinhvien.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/internship")
public class InternshipController {

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private InternshipTimesheetService internshipTimesheetService;

    @Autowired
    private ReviewsService reviewsService;

    @GetMapping({"/", "/homeInternship", ""})
    public String home() {
        return "internship/homeInternship";
    }

    @GetMapping("/registerCalenderWork")
    public String register(@ModelAttribute("internshipTimesheet") InternshipTimesheet internshipTimesheet, Model model) {
        Internship internship = userService.getCurrentUserLogin().getInternship();
        model.addAttribute("internship", internship);
        model.addAttribute("internshipTimesheets", internshipTimesheetService.getAllById(internship.getInternshipId()));
        return "internship/registerCalenderWork";
    }

    @PostMapping("/registerCalenderWork")
    public String save(@Valid InternshipTimesheet internshipTimesheet, Model model) {
        internshipTimesheetService.create(internshipTimesheet);
        return "redirect:/internship/registerCalenderWork";
    }

    @GetMapping("/deleteInternshipTimesheet")
    public String delete(@RequestParam("id") Long id) {
        internshipTimesheetService.deleteById(id);
        return "redirect:/internship/registerCalenderWork";
    }

    @GetMapping("/review")
    public String reviewMentor(@ModelAttribute("review")Reviews reviews, Model model) {
        Internship internship = userService.getCurrentUserLogin().getInternship();
        model.addAttribute("internship", internship);
        model.addAttribute("mentor", mentorService.getMentorById(internship.getMentorId()));
        model.addAttribute("reviews", reviewsService.getAllInternshipReviews(internship.getInternshipId()));
        return "internship/review";
    }

    @PostMapping("review")
    public String saveReviewMentor(@Valid Reviews reviews, Model model) {
        reviewsService.create(reviews);
        return "redirect:/internship/review";
    }

    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam("id") Long id) {
        reviewsService.delete(id);
        return "redirect:/internship/review";
    }
}
