package com.healthcare.target.controller;

import com.healthcare.review.dto.ReviewDto;
import com.healthcare.target.mapper.TargetMapper;
import com.healthcare.target.service.TargetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class TargetController {
    private final TargetService targetService;
    private final TargetMapper mapper;

    public TargetController(TargetService targetService, TargetMapper mapper) {
        this.targetService = targetService;
        this.mapper = mapper;
    }
    //post, get, patch ,delete



}
