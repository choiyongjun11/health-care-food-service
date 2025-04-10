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
@RequestMapping("/health/target")
public class TargetController {
    private final static String TARGET_DEFAULT_URL = "health/target";
    private final TargetService targetService;
    private final TargetMapper mapper;

    public TargetController(TargetService targetService, TargetMapper mapper) {
        this.targetService = targetService;
        this.mapper = mapper;
    }

    //post, get, patch ,delete
    //health/target

    @PostMapping
    public ResponseEntity postTarget(@RequestBody TargetDto.Post requestBody) {

        Target createdTarget = targetService.createTarget(requestBody);
        URI location = UriCreator.createUri(TARGET_DEFAULT_URL, createdTarget.getTargetId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{target-id}")
    public ResponseEntity<SingleResponseDto<TargetDto.Response>> getTarget(@PathVariable("target-id") long targetId) {
        Target target = targetService.findTarget(targetId);
        TargetDto.Response response = mapper.targetToResponse(target);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping("/{target-id}")
    public ResponseEntity<SingleResponseDto<TargetDto.Response>> patchTarget(
            @PathVariable("target-id") long targetId,
            @RequestBody TargetDto.Patch requestBody) {

        requestBody.setTargetId(targetId);
        Target updatedTarget = targetService.updateTarget(requestBody);
        TargetDto.Response response = mapper.targetToResponse(updatedTarget);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{target-id}")
    public ResponseEntity deleteTarget(@PathVariable("target-id") long targetId) {
        targetService.deleteTarget(targetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
