package Gongbok_BE.Gongbok.friendship.domain;

import Gongbok_BE.Gongbok.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "friend1_id")
    private Member friend1;

    @ManyToOne
    @JoinColumn(name = "friend2_id")
    private Member friend2;

    private boolean isAccepted;

    public void acceptFriendship() {
        this.isAccepted = true;
    }
}