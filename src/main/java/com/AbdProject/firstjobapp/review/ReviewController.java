package com.AbdProject.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isSaved = reviewService.addReview(companyId, review);
        if (isSaved)
            return ResponseEntity.ok("Review added successfully");
        else
            return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isSaved = reviewService.updateReview(companyId, reviewId, review);
        if(isSaved)
            return ResponseEntity.ok("Review updated successfully");
        else
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted)
            return ResponseEntity.ok("Review deleted successfully");
        return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
    }
}

