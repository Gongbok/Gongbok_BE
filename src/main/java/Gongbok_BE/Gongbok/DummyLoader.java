package Gongbok_BE.Gongbok;

import Gongbok_BE.Gongbok.member.domain.Role;
import Gongbok_BE.Gongbok.member.domain.SocialType;
import Gongbok_BE.Gongbok.member.domain.Member;
import Gongbok_BE.Gongbok.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DummyLoader {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void loadData(){
        Member member1 = new Member(1L, new ArrayList<>(), "skjn@askjnf.com", "", "루이뷔통통튀기네",
                1999, "", 100, Role.USER, SocialType.APPLE, "", "");
        memberRepository.save(member1);
        Member member2 = new Member(2L, new ArrayList<>(), "skjn@test.com", "", "대학수업",
                2001, "", 50, Role.USER, SocialType.KAKAO, "", "");
        memberRepository.save(member2);
        Member member3 = new Member(3L, new ArrayList<>(), "333@test.com", "", "뚜벅이밴",
                1999, "", 250, Role.USER, SocialType.KAKAO, "", "");
        memberRepository.save(member3);
    }
}
