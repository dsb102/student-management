package com.example.quanlisinhvien.serivce.impl;

import com.example.quanlisinhvien.model.Reviews;
import com.example.quanlisinhvien.repository.ReviewsRepository;
import com.example.quanlisinhvien.serivce.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewsService implements IReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Reviews> getAllInternshipReviews(Long id) {
        return reviewsRepository.findAllByInternship_InternshipIdAndIsDelFlgFalse(id);
    }

    @Override
    public List<Reviews> getAllMentorReviews(Long id) {
        return reviewsRepository.findAllByMentor_MentorIdAndIsDelFlgFalse(id);
    }

    @Override
    public Reviews create(Reviews reviews) {
        reviews.setCreateAt(LocalDateTime.now());
        reviews.setCreatedId(userService.getCurrentUserLogin().getUserId());
        return reviewsRepository.save(reviews);
    }

    @Override
    public void delete(Long id) {
        Reviews review = reviewsRepository.getOne(id);
        review.setDelFlg(true);
        reviewsRepository.save(review);
    }
}
