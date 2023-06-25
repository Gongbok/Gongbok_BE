package Gongbok_BE.Gongbok.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_MEMBER_EMAIL(HttpStatus.CONFLICT,"email duplicated"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"category doesn't exist"),
    STATE_NOT_FOUND(HttpStatus.NOT_FOUND,"state doesn't exist"),
    DUPLICATED_MEMBER_NICKNAME(HttpStatus.CONFLICT,"nickname duplicated"),
    DUPLICATED_FRIENDSHIP(HttpStatus.CONFLICT, "friendship duplicated"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "member doesn't exist"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is not matched"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post doesn't exist"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment doesn't exist"),
    FRIENDSHIP_NOT_FOUND(HttpStatus.NOT_FOUND, "friendship doesn't exist"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "token is not valid."),
    INVALID_TOKEN_LOGOUT(HttpStatus.UNAUTHORIZED, "token is not valid because you logged out."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "authorization failed"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "database error");
    private HttpStatus status;
    private String message;
}
