package Gongbok_BE.Gongbok.member.controller;

import Gongbok_BE.Gongbok.auth.jwt.service.JwtService;
import Gongbok_BE.Gongbok.response.Response;
import Gongbok_BE.Gongbok.member.dto.MemberRequestDto;
import Gongbok_BE.Gongbok.member.dto.MemberResponseDto;
import Gongbok_BE.Gongbok.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {
    private final JwtService jwtService;
    private final MemberService memberService;

    @GetMapping("/logout")
    public Response<MemberResponseDto.logout> logout() {
        return Response.success(jwtService.logout());
    }

    @GetMapping
    public Response<MemberResponseDto.profile> getInfo() {
        return Response.success(memberService.getInfo());
    }

    @DeleteMapping
    public Response<MemberResponseDto.withdraw> withdraw(){
        return Response.success(memberService.withdraw());
    }

    @PatchMapping
    public Response<MemberResponseDto.profile> updateProfile(@RequestBody MemberRequestDto requestDto){
        return Response.success(memberService.updateProfile(requestDto));
    }

    @GetMapping("/star")
    public Response<MemberResponseDto.star> getStarNum(){
        return Response.success(memberService.getStarNum());
    }

    @GetMapping("/history")
    public Response<List<MemberResponseDto.starHistory>> getStarHistory(){
        return Response.success(memberService.getStarHistory());
    }

    @GetMapping("/ranking")
    public Response<Slice<MemberResponseDto.starRank>> getAllRank(Pageable pageable){
        return Response.success(memberService.getAllRank(pageable));
    }

    @GetMapping("/ranking/same_age")
    public Response<Slice<MemberResponseDto.starRank>> getSameAgeRank(Pageable pageable){
        return Response.success(memberService.getSameAgeRank(pageable));
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}