package Gongbok_BE.Gongbok.friendship.dto;

import lombok.Builder;
import lombok.Data;

public class FriendshipResponseDto {

    @Data
    @Builder
    public static class result {
        private Long id;
        private String message;
    }

    @Data
    @Builder
    public static class friend {
        private Long id;
        private String nickname;
        private int starNum;
    }
}
