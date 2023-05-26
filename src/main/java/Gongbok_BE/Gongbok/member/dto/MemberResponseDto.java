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

    @Data
    @Builder
    public static class star {
        private int starNum;
    }

    @Data
    @Builder
    public static class starHistory {
        private String reason;
        private int starNum;
    }

    @Data
    @Builder
    public static class starRank {
        private Long memberId;
        private String nickname;
        private int birthYear;
        private int starNum;
    }

//    @Data
//    @Builder
//    public static class starRank {
//        private String nickname;
//        private int starNum;
//    }

}
