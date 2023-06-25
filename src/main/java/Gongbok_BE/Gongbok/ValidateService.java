package Gongbok_BE.Gongbok;

import Gongbok_BE.Gongbok.config.SecurityUtil;
import Gongbok_BE.Gongbok.exception.AllGongbokException;
import Gongbok_BE.Gongbok.exception.ErrorCode;
import Gongbok_BE.Gongbok.friendship.domain.Friendship;
import Gongbok_BE.Gongbok.friendship.repository.FriendshipRepository;
import Gongbok_BE.Gongbok.member.domain.Member;
import Gongbok_BE.Gongbok.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ValidateService {
    private final MemberRepository memberRepository;
    private final FriendshipRepository friendshipRepository;

    // Access Token으로 회원 검증 및 불러오기
    public Member validateMember() {
        String email = SecurityUtil.getEmail();
        return validateMemberByEmail(email);
    }

    // Email로 회원 불러오기
    public Member validateMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new AllGongbokException(ErrorCode.MEMBER_NOT_FOUND,
                        ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }

    // 닉네임으로 회원 불러오기
    public Member validateMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(() ->
                new AllGongbokException(ErrorCode.MEMBER_NOT_FOUND,
                        ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }

    // 회원 ID로 회원 불러오기
    public Member validateMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(()->
                new AllGongbokException(ErrorCode.MEMBER_NOT_FOUND, ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }
    
    // 친구 관계 ID로 친구 관계 불러오기
    public Friendship validateFriendship(Long id){
        return friendshipRepository.findById(id).orElseThrow(()->
                new AllGongbokException(ErrorCode.FRIENDSHIP_NOT_FOUND, ErrorCode.FRIENDSHIP_NOT_FOUND.getMessage()));
    }

    // 이미 맺어진 친구 관계가 있는지 확인
    public boolean isFriendshipExists(Member member1, Member member2){
        return friendshipRepository.existsByFriend1AndFriend2OrFriend1AndFriend2(member1, member2,
                member2, member1);
    }
}
