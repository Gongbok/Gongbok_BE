package Gongbok_BE.Gongbok.member.service;

import Gongbok_BE.Gongbok.ValidateService;
import Gongbok_BE.Gongbok.friendship.dto.FriendshipResponseDto;
import Gongbok_BE.Gongbok.friendship.service.FriendshipService;
import Gongbok_BE.Gongbok.member.domain.Role;
import Gongbok_BE.Gongbok.member.domain.Member;
import Gongbok_BE.Gongbok.member.dto.MemberRequestDto;
import Gongbok_BE.Gongbok.member.dto.MemberResponseDto;
import Gongbok_BE.Gongbok.member.repository.MemberRepository;
import Gongbok_BE.Gongbok.member.repository.StarHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final ValidateService validateService;
    private final FriendshipService friendshipService;
    private final MemberRepository memberRepository;
    private final StarHistoryRepository starHistoryRepository;

    // 회원 탈퇴
    public MemberResponseDto.withdraw withdraw() {
        Member member = validateService.validateMember();
        memberRepository.deleteById(member.getId());
        return MemberResponseDto.withdraw.builder().id(member.getId()).message("회원 탈퇴 완료").build();
    }

    // 닉네임 변경
    public MemberResponseDto.profile updateProfile(MemberRequestDto requestDto) {
        Member member = validateService.validateMember();

        // 회원 가입시 이 메서드를 사용할 때를 대비하여 자동으로 회원 권한을 부여
        if(member.getRole() != Role.USER) member.authorizeMember();

        member.updateNickname(requestDto.getNickname());
        member.updateBirthYear(requestDto.getBirthYear());
        return MemberResponseDto.profile.builder().nickname(requestDto.getNickname())
                .birthYear(requestDto.getBirthYear()).build();
    }

    // 내 정보 얻어오기(프로필 수정 시 input placeholder에 들어갈 값 제공을 위함)
    public MemberResponseDto.profile getInfo(){
        Member member = validateService.validateMember();
        return MemberResponseDto.profile.builder().nickname(member.getNickname())
                .birthYear(member.getBirthYear()).build();
    }
    
    // 내 별 수 얻어오기
    public MemberResponseDto.star getStarNum() {
        Member member = validateService.validateMember();
        return MemberResponseDto.star.builder().starNum(member.getStarNum()).build();
    }

    // 별 획득 내역 얻어오기
    public List<MemberResponseDto.starHistory> getStarHistory() {
        Member member = validateService.validateMember();
        return starHistoryRepository.findByMember_Id(member.getId())
                .stream()
                .map(h -> MemberResponseDto.starHistory.builder()
                        .reason(h.getReason())
                        .starNum(h.getStarNum())
                        .build())
                .collect(Collectors.toList());
    }

    public Slice<MemberResponseDto.starRank> getAllRank(Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "starNum");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Slice<Member> memberSlice = memberRepository.findAll(pageable);
        return getStarRanks(pageable, memberSlice);
    }

    public Slice<MemberResponseDto.starRank> getSameAgeRank(Pageable pageable) {
        Member member = validateService.validateMember();
        Slice<Member> memberSlice = memberRepository.findByBirthYearOrderByStarNumDesc(member.getBirthYear(), pageable);
        return getStarRanks(pageable, memberSlice);
    }

    public Slice<MemberResponseDto.starRank> getFriendRank(Pageable pageable) {
        List<FriendshipResponseDto.friend> allFriends = friendshipService.findAllFriends();
        List<MemberResponseDto.starRank> sortedFriendList = allFriends.stream()
                .sorted(Comparator.comparingInt(FriendshipResponseDto.friend::getStarNum).reversed())
                .map(friend -> MemberResponseDto.starRank.builder()
                        .memberId(friend.getId())
                        .nickname(friend.getNickname())
                        .starNum(friend.getStarNum())
                        .build())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<MemberResponseDto.starRank> pagedFriendList;

        if (sortedFriendList.size() < startItem) {
            pagedFriendList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sortedFriendList.size());
            pagedFriendList = sortedFriendList.subList(startItem, toIndex);
        }
        return new SliceImpl<>(pagedFriendList, pageable, sortedFriendList.size() > (startItem + pageSize));
    }

    private Slice<MemberResponseDto.starRank> getStarRanks(Pageable pageable, Slice<Member> memberSlice) {
        List<MemberResponseDto.starRank> starRankList = memberSlice.getContent().stream()
                .map(m -> MemberResponseDto.starRank.builder()
                        .memberId(m.getId())
                        .nickname(m.getNickname())
                        .birthYear(m.getBirthYear())
                        .starNum(m.getStarNum())
                        .build())
                .collect(Collectors.toList());
        return new SliceImpl<>(starRankList, pageable, memberSlice.hasNext());
    }
}
