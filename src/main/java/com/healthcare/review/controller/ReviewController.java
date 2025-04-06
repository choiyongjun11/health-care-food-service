package com.healthcare.review.controller;

import com.healthcare.response.MultiResponseDto;
import com.healthcare.response.SingleResponseDto;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import com.healthcare.review.mapper.ReviewMapper;
import com.healthcare.review.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/foods")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    public ReviewController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }
    //get. post, patch. delete

    //리뷰: 이 음식 정말 맛있네요. 괜찮은 편이네요. 맛이 없어요. 재료 가격이 너무 비싸요. 능이백숙 건강한 맛이네요.

    @PostMapping("/{food-id}/reviews")
    public ResponseEntity<SingleResponseDto<ReviewDto.Response>> postReview(
            @PathVariable("food-id") long foodId,
            @RequestParam("memberId") long memberId,
            @RequestBody ReviewDto.Post requestBody) {

        Review review = mapper.reviewPostToReview(requestBody);
        Review createdReview = reviewService.createReview(review, foodId, memberId);
        ReviewDto.Response response = mapper.reviewToResponse(createdReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SingleResponseDto<>(response));
    }

    @GetMapping("/{food-id}/reviews")
    public ResponseEntity<?> getReview(@PathVariable("food-id") long foodId,
                                       @RequestParam int page, @RequestParam int size) {
        Page<Review> reviewPage = reviewService.getReviews(foodId, page, size);
        List<ReviewDto.Response> responses = reviewPage.getContent()
                .stream().map(mapper::reviewToResponse).collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(responses,reviewPage));

    }

    @PatchMapping("/{food-id}/reviews/{review-id}")
    public ResponseEntity<SingleResponseDto<ReviewDto.Response>> patchReview(@PathVariable("food-id") long foodId,
                                                                             @PathVariable("review-id") long reviewId,
                                                                             @RequestBody ReviewDto.Patch requestBody) {
        requestBody.setReviewId(reviewId);
        Review review = reviewService.updateReview(mapper.reviewPatchToReview(requestBody), foodId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.reviewToResponse(review)),HttpStatus.OK);

    }

    @DeleteMapping("/{food-id}/reviews/{review-id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable("food-id") long foodId,
            @PathVariable("review-id") long reviewId) {

        reviewService.deleteReview(reviewId, foodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
