package Gongbok_BE.Gongbok.friendship.repository;

import Gongbok_BE.Gongbok.friendship.domain.Friendship;
import Gongbok_BE.Gongbok.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    boolean existsByFriend1AndFriend2OrFriend1AndFriend2(Member member1, Member member2, Member member21, Member member11);
    List<Friendship> findAllByFriend1AndIsAccepted(Member member, boolean isAccepted);
    List<Friendship> findAllByFriend2AndIsAccepted(Member member, boolean isAccepted);

}
