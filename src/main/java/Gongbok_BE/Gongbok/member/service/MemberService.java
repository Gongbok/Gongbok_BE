package Gongbok_BE.Gongbok.member.service;

import Gongbok_BE.Gongbok.config.SecurityUtil;
import Gongbok_BE.Gongbok.exception.ErrorCode;
import Gongbok_BE.Gongbok.member.domain.Role;
import Gongbok_BE.Gongbok.member.domain.Member;
import Gongbok_BE.Gongbok.member.dto.MemberRequestDto;
import Gongbok_BE.Gongbok.member.dto.MemberResponseDto;
import Gongbok_BE.Gongbok.exception.AllGongbokException;
import Gongbok_BE.Gongbok.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 탈퇴
    public MemberResponseDto.withdraw withdraw() {
        String email = SecurityUtil.getEmail();
        Member member = validateMember(email);
        memberRepository.deleteById(member.getId());
        return MemberResponseDto.withdraw.builder().id(member.getId()).message("회원 탈퇴 완료").build();
    }

    // 닉네임 변경
    public MemberResponseDto.profile updateProfile(MemberRequestDto requestDto) {
        String email = SecurityUtil.getEmail();
        Member member = validateMember(email);

        // 회원 가입시 이 메서드를 사용할 때를 대비하여 자동으로 회원 권한을 부여
        if(member.getRole() != Role.USER) member.authorizeMember();
        member.updateNickname(requestDto.getNickname());
        member.updateBirthYear(requestDto.getBirthYear());
        return MemberResponseDto.profile.builder().nickname(requestDto.getNickname())
                .birthYear(requestDto.getBirthYear()).build();
    }

    // 회원 검증 및 불러오기
    private Member validateMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new AllGongbokException(ErrorCode.MEMBER_NOT_FOUND,
                        ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }

    // 내 정보 얻어오기(프로필 수정 시 input placeholder에 들어갈 값 제공을 위함)
    public MemberResponseDto.profile getInfo(){
        String email = SecurityUtil.getEmail();
        Member member = validateMember(email);
        return MemberResponseDto.profile.builder().nickname(member.getNickname())
                .birthYear(member.getBirthYear()).build();
    }
}
