package com.healthcare.member.controller;

import com.healthcare.response.SingleResponseDto;
import com.healthcare.member.dto.MemberDto;
import com.healthcare.member.entity.Member;
import com.healthcare.member.mapper.MemberMapper;
import com.healthcare.member.service.MemberService;
import com.healthcare.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping("/members")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members"; //url 주소
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.Post requestBody) {
        Member member = mapper.memberPostToMember(requestBody);
        Member createdMember = memberService.createMember(member);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId, @RequestBody MemberDto.Patch requestBody) {
        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(mapper.memberPatchToMember(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)),HttpStatus.OK);

    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
