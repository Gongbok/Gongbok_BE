package Gongbok_BE.Gongbok.member.dto;

import lombok.Builder;
import lombok.Data;

public class MemberResponseDto {

    @Data
    @Builder
    public static class withdraw {
        private String message;
        private Long id;
    }

    @Data
    @Builder
    public static class logout {
        private String message;
    }

    @Data
    @Builder
    public static class profile{
        private String nickname;
        private int birthYear;
    }
}
