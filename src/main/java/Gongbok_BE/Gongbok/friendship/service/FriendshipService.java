package Gongbok_BE.Gongbok.friendship.service;

import Gongbok_BE.Gongbok.ValidateService;
import Gongbok_BE.Gongbok.exception.AllGongbokException;
import Gongbok_BE.Gongbok.exception.ErrorCode;
import Gongbok_BE.Gongbok.friendship.domain.Friendship;
import Gongbok_BE.Gongbok.friendship.dto.FriendshipResponseDto;
import Gongbok_BE.Gongbok.friendship.repository.FriendshipRepository;
import Gongbok_BE.Gongbok.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendshipService {

    private final ValidateService validateService;
    private final FriendshipRepository friendshipRepository;

    public FriendshipResponseDto.result addFriendship(Long memberId) {
        Member member1 = validateService.validateMember();
        Member member2 = validateService.validateMemberById(memberId);
        if(validateService.isFriendshipExists(member1, member2)) {
            throw new AllGongbokException(ErrorCode.DUPLICATED_FRIENDSHIP,ErrorCode.DUPLICATED_FRIENDSHIP.getMessage());
        }
        Friendship friendship = Friendship.builder().friend1(member1).friend2(member2)
                .isAccepted(false).build();
        Friendship save = friendshipRepository.save(friendship);
        return FriendshipResponseDto.result.builder().id(save.getId())
                .message("친구 요청 완료").build();
    }

    public List<FriendshipResponseDto.friend> findAllFriends() {
        Member member = validateService.validateMember();
        List<FriendshipResponseDto.friend> friendList1 = friendshipRepository.findAllByFriend1AndIsAccepted(member, true)
                .stream().map(f -> FriendshipResponseDto.friend.builder().id(f.getId()).nickname(f.getFriend2().getNickname())
                        .starNum(f.getFriend2().getStarNum()).build()).collect(Collectors.toList());
        List<FriendshipResponseDto.friend> friendList2 = friendshipRepository.findAllByFriend2AndIsAccepted(member, true)
                .stream().map(f -> FriendshipResponseDto.friend.builder().id(f.getId()).nickname(f.getFriend1().getNickname())
                        .starNum(f.getFriend1().getStarNum()).build()).collect(Collectors.toList());
        friendList1.addAll(friendList2);
        return friendList1;
    }

    public FriendshipResponseDto.friend findMemberByNickname(String nickname) {
        Member member = validateService.validateMemberByNickname(nickname);
        return FriendshipResponseDto.friend.builder().id(member.getId())
                .nickname(member.getNickname()).starNum(member.getStarNum()).build();
    }

    public List<FriendshipResponseDto.friend> checkPendingRequests() {
        Member member = validateService.validateMember();
        List<FriendshipResponseDto.friend> friendList = friendshipRepository.findAllByFriend2AndIsAccepted(member, false)
                .stream().map(f -> FriendshipResponseDto.friend.builder().id(f.getId()).nickname(f.getFriend1().getNickname())
                        .starNum(f.getFriend1().getStarNum()).build()).collect(Collectors.toList());
        return friendList;
    }

    public FriendshipResponseDto.result acceptFriendship(Long friendId) {
        Friendship friendship = validateService.validateFriendship(friendId);
        friendship.acceptFriendship();
        return FriendshipResponseDto.result.builder().id(friendId)
                .message("친구 수락 완료").build();
    }

    public FriendshipResponseDto.result deleteFriend(Long friendId) {
        friendshipRepository.deleteById(friendId);
        return FriendshipResponseDto.result.builder().id(friendId).message("친구 삭제 완료").build();
    }
}