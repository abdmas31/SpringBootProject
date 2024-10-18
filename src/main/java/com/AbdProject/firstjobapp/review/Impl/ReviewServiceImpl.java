package com.AbdProject.firstjobapp.review.Impl;

import com.AbdProject.firstjobapp.company.Company;
import com.AbdProject.firstjobapp.company.CompanyService;
import com.AbdProject.firstjobapp.review.Review;
import com.AbdProject.firstjobapp.review.ReviewRepository;
import com.AbdProject.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);

        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            for (Review r : reviews) {
                if (r.getId().equals(reviewId)) {
                    r.setDescription(review.getDescription());
                    r.setRating(review.getRating());
                    r.setTitle(review.getTitle());
                    reviewRepository.save(r);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            assert review != null;
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;

        } else {
            return false;
        }
    }
}