package com.example.quanlisinhvien.serivce;

import com.example.quanlisinhvien.model.Reviews;

import java.util.List;

public interface IReviewsService {
    List<Reviews> getAllInternshipReviews(Long id);

    List<Reviews> getAllMentorReviews(Long id);

    Reviews create(Reviews reviews);

    void delete(Long id);
}
