package com.healthcare.member.service;

import com.healthcare.auth.utils.AuthorityUtils;
import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.dto.FoodDto;
import com.healthcare.food.mapper.FoodMapper;
import com.healthcare.food.mapper.FoodLikeMapper;
import com.healthcare.food.repository.FoodLikeRepository;
import com.healthcare.member.dto.MemberDto;
import com.healthcare.member.entity.Member;
import com.healthcare.member.repository.MemberRepository;
import com.healthcare.member.repository.MemberTargetRepository;
import com.healthcare.review.dto.ReviewDto;
import com.healthcare.review.mapper.ReviewMapper;
import com.healthcare.review.repository.ReviewRepository;
import com.healthcare.target.dto.TargetDto;
import com.healthcare.target.mapper.TargetMapper;

import com.healthcare.target.repository.TargetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; //spring security jwt 토큰 인증을 위해
    private final AuthorityUtils authorityUtils; //spring security jwt 토큰 인증을 위해 우리가 만든 AuthorityUtils 클래스를 활용한다.

    private final FoodLikeRepository foodLikeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberTargetRepository memberTargetRepository;

    private final FoodMapper foodMapper;
    private final ReviewMapper reviewMapper;
    private final TargetMapper targetMapper;
    private final FoodLikeMapper foodLikeMapper;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
                         AuthorityUtils authorityUtils, FoodLikeRepository foodLikeRepository,
                         ReviewRepository reviewRepository, MemberTargetRepository memberTargetRepository,
                         FoodMapper foodMapper, ReviewMapper reviewMapper, TargetMapper targetMapper, FoodLikeMapper foodLikeMapper) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.foodLikeRepository = foodLikeRepository;
        this.reviewRepository = reviewRepository;
        this.memberTargetRepository = memberTargetRepository;

        this.foodMapper = foodMapper;
        this.reviewMapper = reviewMapper;
        this.targetMapper = targetMapper;

        this.foodLikeMapper = foodLikeMapper;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        //password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);
        //이메일 기준으로 권한 정보 추가
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    public MemberDto.MemberActivityResponse getMemberActivities(Long memberId) {
        Member member = findVerifiedMember(memberId);

        Set<Long> likedFoodIds = foodLikeRepository.findWithFoodByMemberId(memberId)
                .stream()
                .map(foodLike -> foodLike.getFood().getFoodId())
                .collect(Collectors.toSet());

        List<FoodDto.LikeResponse> likedFoods = foodLikeRepository.findWithFoodByMemberId(memberId)
                .stream()
                .map(foodLike -> foodLikeMapper.foodToLikeResponse(
                        foodLike.getFood(),
                        (int) foodLike.getLikeCount(),
                        likedFoodIds.contains(foodLike.getFood().getFoodId())
                ))
                .collect(Collectors.toList());

        List<ReviewDto.Response> writtenReviews = reviewRepository.findByMember_MemberId(memberId)
                .stream().map(reviewMapper::reviewToResponse).collect(Collectors.toList());

        List<TargetDto.Response> healthTargets = memberTargetRepository.findByMember_MemberId(memberId)
                .stream()
                .map(memberTarget -> targetMapper.targetToResponse(memberTarget.getTarget())) //memberTarget 안의 target
                .collect(Collectors.toList());

        return new MemberDto.MemberActivityResponse(likedFoods, writtenReviews, healthTargets);
    }

    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        return memberRepository.findAll(pageable);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());
        Optional.ofNullable(member.getName()).ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getBirthday()).ifPresent(birthday -> findMember.setBirthday(birthday));
        Optional.ofNullable(member.getPhone()).ifPresent(phone -> findMember.setPhone(phone));
        return memberRepository.save(findMember);
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    //member id 로 검증
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findMember;
    }

    //email 규칙 검증
    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
    }



}
