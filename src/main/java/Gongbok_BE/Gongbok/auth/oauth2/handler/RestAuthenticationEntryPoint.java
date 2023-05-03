package Gongbok_BE.Gongbok.auth.oauth2.handler;

import Gongbok_BE.Gongbok.exception.ErrorCode;
import Gongbok_BE.Gongbok.response.ErrorResponse;
import Gongbok_BE.Gongbok.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // HTTP 응답 상태 코드를 설정합니다.
        response.setStatus(ErrorCode.INVALID_TOKEN.getStatus().value());
        // HTTP 응답 헤더를 설정합니다.
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // HTTP 응답 본문을 생성합니다.
        Response<ErrorResponse> errorResponse = Response.error(new ErrorResponse(ErrorCode.INVALID_TOKEN,
                ErrorCode.INVALID_TOKEN.getMessage()));
        // HTTP 응답 본문을 JSON 형태로 변환합니다.
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(errorResponse);
        // HTTP 응답 본문을 전송합니다.
        response.getWriter().write(json);
    }
}
