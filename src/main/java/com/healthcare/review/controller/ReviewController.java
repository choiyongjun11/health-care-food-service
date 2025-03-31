package com.healthcare.review.controller;

import com.healthcare.response.MultiResponseDto;
import com.healthcare.response.SingleResponseDto;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import com.healthcare.review.mapper.ReviewMapper;
import com.healthcare.review.service.ReviewService;
import com.healthcare.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final static String REVIEWS_DEFAULT_URL = "/reviews";
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    public ReviewController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }

        //get. post, patch. delete

    @PostMapping
    public ResponseEntity postReview(@RequestBody ReviewDto.Post requestBody) {
        Review review = mapper.reviewPostToReview(requestBody);
        Review createReview = reviewService.createReview(review);
        URI location = UriCreator.createUri(REVIEWS_DEFAULT_URL, createReview.getReviewId());
        return ResponseEntity.created(location).build();

    }

    @PatchMapping("/{reviews-id}")
    public ResponseEntity patchReview(@PathVariable("reviews-id") long reviewId, @RequestBody ReviewDto.Patch requestBody) {
        requestBody.setReviewId(reviewId);
        Review review = reviewService.updateReview(mapper.reviewPatchToReview(requestBody));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.reviewToResponse(review)),HttpStatus.OK);

    }

    //음식에 대한 리뷰 조회
//    @GetMapping
//    public ResponseEntity getReviewByFood(@PathVariable("food-id") long foodId, Pageable pageable) {
//
//    }

    @DeleteMapping("/{reviews-id}")
    public ResponseEntity deleteReview(@PathVariable("reviews-id") long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
