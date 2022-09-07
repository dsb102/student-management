package com.example.quanlisinhvien.controller;

import com.example.quanlisinhvien.model.Mentor;
import com.example.quanlisinhvien.model.Reviews;
import com.example.quanlisinhvien.serivce.impl.InternshipService;
import com.example.quanlisinhvien.serivce.impl.ReviewsService;
import com.example.quanlisinhvien.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private ReviewsService reviewsService;

    @GetMapping({"/", "/homeMentor", ""})
    public String home() {
        return "mentor/homeMentor";
    }

    @GetMapping("/review")
    public String reviewMentor(@ModelAttribute("review") Reviews reviews, Model model) {
        Mentor mentor = userService.getCurrentUserLogin().getMentor();
        model.addAttribute("mentor", mentor);
        model.addAttribute("internships", internshipService.getAllByMentorId(mentor.getMentorId()));
        model.addAttribute("reviews", reviewsService.getAllMentorReviews(mentor.getMentorId()));
        return "mentor/review";
    }

    @PostMapping("review")
    public String saveReviewMentor(@Valid Reviews reviews, Model model) {
        reviewsService.create(reviews);
        return "redirect:/mentor/review";
    }

    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam("id") Long id) {
        reviewsService.delete(id);
        return "redirect:/mentor/review";
    }
}
