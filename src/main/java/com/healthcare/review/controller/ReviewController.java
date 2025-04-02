package com.healthcare.review.controller;

import com.healthcare.response.MultiResponseDto;
import com.healthcare.response.SingleResponseDto;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.entity.Review;
import com.healthcare.review.mapper.ReviewMapper;
import com.healthcare.review.service.ReviewService;
import com.healthcare.utils.UriCreator;


import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final static String REVIEWS_DEFAULT_URL = "/foods";
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    public ReviewController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }

        //get. post, patch. delete

    //리뷰: 이 음식 정말 맛있네요. 괜찮은 편이네요. 맛이 없어요. 재료 가격이 너무 비싸요. 능이백숙 건강한 맛이네요.


    @PostMapping("/{food-id}/reviews")
    public ResponseEntity<ReviewDto.Response> postReview(@PathVariable("food-id") long foodId,
                                                         @RequestBody ReviewDto.Post requestBody) {
        Review review = mapper.reviewPostToReview(requestBody);
        long memberId = 1L;
        Review createdReview = reviewService.createReview(review, foodId, memberId);
        ReviewDto.Response response = mapper.reviewToResponse(createdReview);
        response.setMessage("리뷰 등록이 완료되었습니다.");

        URI location = URI.create(String.format("/foods/%d/reviews/%d", foodId, createdReview.getReviewId()));
        return ResponseEntity.created(location).body(response);
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
        Review review = mapper.reviewPatchToReview(requestBody); //등록된 리뷰의 dto -> entity로 받아옵니다.
        Review updated = reviewService.updateReview(review, foodId);
        ReviewDto.Response response = mapper.reviewToResponse(updated);
        response.setMessage("리뷰 내용이 변경되었습니다.");
        return ResponseEntity.ok(new SingleResponseDto<>(response));
    }

    @DeleteMapping("/{food-id}/reivews/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("food-id") long foodId,
                                       @PathVariable("review-id") long reviewId) {
        reviewService.deleteReview(reviewId, foodId);
        return ResponseEntity.ok(new SingleResponseDto<>("해당 리뷰가 삭제되었습니다."));

    }


}
