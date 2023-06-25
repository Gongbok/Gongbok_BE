package Gongbok_BE.Gongbok.friendship.controller;

import Gongbok_BE.Gongbok.friendship.dto.FriendshipResponseDto;
import Gongbok_BE.Gongbok.friendship.service.FriendshipService;
import Gongbok_BE.Gongbok.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/friend")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/{memberId}")
    public Response<FriendshipResponseDto.result> addFriend(@PathVariable Long memberId){
        return Response.success(friendshipService.addFriendship(memberId));
    }

    @GetMapping
    public Response<List<FriendshipResponseDto.friend>> findAllFriends(){
        return Response.success(friendshipService.findAllFriends());
    }

    @GetMapping("/search/{nickname}")
    public Response<FriendshipResponseDto.friend> findMemberByNickname(@PathVariable String nickname){
        return Response.success(friendshipService.findMemberByNickname(nickname));
    }

    @GetMapping("/pending")
    public Response<List<FriendshipResponseDto.friend>> checkPendingRequests(){
        return Response.success(friendshipService.checkPendingRequests());
    }

    @PatchMapping("/{friendId}")
    public Response<FriendshipResponseDto.result> acceptFriendship(@PathVariable Long friendId){
        return Response.success(friendshipService.acceptFriendship(friendId));

    }

    @DeleteMapping("/{friendId}")
    public Response<FriendshipResponseDto.result> deleteFriend(@PathVariable Long friendId){
        return Response.success(friendshipService.deleteFriend(friendId));
    }
}