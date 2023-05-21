package Gongbok_BE.Gongbok.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<StarHistory> starHistories = new ArrayList<>();

    private String email;
    private String password;
    private String nickname;
    private int birthYear;
    private String profileImage;
    private int starNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // APPLE, KAKAO

    private String socialId; // 로그인한 소셜 타입의 식별자 값
    private String refreshToken; // 리프레시 토큰

    // 유저 권한 설정 메소드
    public void authorizeMember() {
        this.role = Role.USER;
    }

    // RefreshToken 재발급 시 사용할 메소드
    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    public void updateNickname(String nickname) { this.nickname = nickname; }

    public void updateBirthYear(int birthYear) { this.birthYear = birthYear; }
}
