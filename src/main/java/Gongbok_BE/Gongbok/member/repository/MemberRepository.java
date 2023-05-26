package Gongbok_BE.Gongbok.member.repository;

import Gongbok_BE.Gongbok.member.domain.SocialType;
import Gongbok_BE.Gongbok.member.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String id);

    Slice<Member> findByBirthYearOrderByStarNumDesc(int birthYear, Pageable pageable);
}
