package Gongbok_BE.Gongbok;

import Gongbok_BE.Gongbok.config.SecurityUtil;
import Gongbok_BE.Gongbok.exception.AllGongbokException;
import Gongbok_BE.Gongbok.exception.ErrorCode;
import Gongbok_BE.Gongbok.member.domain.Member;
import Gongbok_BE.Gongbok.member.domain.Role;
import Gongbok_BE.Gongbok.member.dto.MemberRequestDto;
import Gongbok_BE.Gongbok.member.dto.MemberResponseDto;
import Gongbok_BE.Gongbok.member.repository.MemberRepository;
import Gongbok_BE.Gongbok.member.repository.StarHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ValidateService {
    private final MemberRepository memberRepository;

    // Access Token으로 회원 검증 및 불러오기
    public Member validateMember() {
        String email = SecurityUtil.getEmail();
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new AllGongbokException(ErrorCode.MEMBER_NOT_FOUND,
                        ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }

    // Email로 회원 불러오기
    public Member validateMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new AllGongbokException(ErrorCode.MEMBER_NOT_FOUND,
                        ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }
}
