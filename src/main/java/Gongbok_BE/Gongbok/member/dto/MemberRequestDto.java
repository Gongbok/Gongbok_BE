package Gongbok_BE.Gongbok.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequestDto {
    private String nickname;
    private int birthYear;
}
