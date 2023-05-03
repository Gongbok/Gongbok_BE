package Gongbok_BE.Gongbok.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T result;

    public static <T> Response<T> error(T result){
        return new Response<>("ERROR", result);
    }
    public static <T> Response<T> success(T result){
        return new Response<>("SUCCESS", result);
    }
}
