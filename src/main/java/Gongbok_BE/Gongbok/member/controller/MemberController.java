package Gongbok_BE.Gongbok.member.controller;

import Gongbok_BE.Gongbok.auth.jwt.service.JwtService;
import Gongbok_BE.Gongbok.response.Response;
import Gongbok_BE.Gongbok.member.dto.MemberRequestDto;
import Gongbok_BE.Gongbok.member.dto.MemberResponseDto;
import Gongbok_BE.Gongbok.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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
        MemberResponseDto.logout logoutResponse = jwtService.logout();
        return Response.success(logoutResponse);
    }

    @GetMapping
    public Response<MemberResponseDto.profile> getInfo() {
        MemberResponseDto.profile profileResponse = memberService.getInfo();
        return Response.success(profileResponse);
    }

    @DeleteMapping
    public Response<MemberResponseDto.withdraw> withdraw(){
        MemberResponseDto.withdraw withdrawResponse = memberService.withdraw();
        return Response.success(withdrawResponse);
    }

    @PatchMapping
    public Response<MemberResponseDto.profile> updateProfile(@RequestBody MemberRequestDto requestDto){
        MemberResponseDto.profile updateProfileResponse =
                memberService.updateProfile(requestDto);
        return Response.success(updateProfileResponse);
    }

    @GetMapping("/star")
    public Response<MemberResponseDto.star> getStarNum(){
        MemberResponseDto.star starResponse = memberService.getStarNum();
        return Response.success(starResponse);
    }

    @GetMapping("/history")
    public Response<List<MemberResponseDto.starHistory>> getStarHistory(){
        List<MemberResponseDto.starHistory> historyResponse = memberService.getStarHistory();
        return Response.success(historyResponse);
    }

//    @GetMapping("")
//    public Response<List<MemberResponseDto.starRank>> getAllRank(){
//        List<MemberResponseDto.starRank> starRankList = memberService.getAllRank();
//    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}