package com.AbdProject.firstjobapp.review.Impl;

import com.AbdProject.firstjobapp.review.Review;
import com.AbdProject.firstjobapp.review.ReviewRepository;
import com.AbdProject.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

}
