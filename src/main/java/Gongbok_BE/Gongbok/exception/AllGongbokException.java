package Gongbok_BE.Gongbok.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllGongbokException extends RuntimeException {
    private ErrorCode errorCode;
    private String string;
}
