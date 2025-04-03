package com.healthcare.target.controller;

import com.healthcare.response.SingleResponseDto;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.entity.Target;
import com.healthcare.target.mapper.TargetMapper;
import com.healthcare.target.service.TargetService;
import com.healthcare.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/helath")
public class TargetController {
    private final static String TARGET_DEFAULT_URL = "health/target";
    private final TargetService targetService;
    private final TargetMapper mapper;

    public TargetController(TargetService targetService, TargetMapper mapper) {
        this.targetService = targetService;
        this.mapper = mapper;
    }
    //post, get, patch ,delete

    @PostMapping
    public ResponseEntity postTarget(@RequestBody TargetDto.Post requestBody) {
        Target target = mapper.targetPostToTarget(requestBody);
        Target createTarget = targetService.createTarget(target);
        URI location = UriCreator.createUri(TARGET_DEFAULT_URL, createTarget.getTargetId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{target-id}")
    public ResponseEntity patchTarget(@PathVariable("target-id") long targetId, @RequestBody TargetDto.Patch requestBody) {
        requestBody.setTargetId(targetId);
        Target target = targetService.updateTarget(mapper.targetPatchToTarget(requestBody));
        targetService.updateTarget(mapper.targetPatchToTarget(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.targetToResponse(target)),HttpStatus.OK);

    }

    @GetMapping("/{target-id}")
    public ResponseEntity getTarget(@PathVariable("target-id") long targetId) {
        Target target = targetService.findTarget(targetId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.targetToResponse(target)), HttpStatus.OK);

    }

    @DeleteMapping("/{target-id}")
    public ResponseEntity deleteTarget(@PathVariable("target-id") long targetId) {
        targetService.deleteTarget(targetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
