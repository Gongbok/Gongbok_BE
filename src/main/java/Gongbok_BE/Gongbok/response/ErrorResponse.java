package Gongbok_BE.Gongbok.response;

import Gongbok_BE.Gongbok.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;
}