package com.todostudy.myjob.global.https;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todostudy.myjob.standard.base.Empty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import static lombok.AccessLevel.PRIVATE;
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE, force = true)
@Getter
public class RespData<T> {
    public static final RespData<Empty> OK = of("200-1", "성공", new Empty());
    @NonNull
    String resultCode;
    @NonNull
    int statusCode;
    @NonNull
    String msg;
    @NonNull
    T data;

    public static RespData<Empty> of(String msg) {
        return of("200-0", msg, new Empty());
    }

    public static <T> RespData<T> of(T data) {
        return of("200-0", "성공", data);
    }

    public static <T> RespData<T> of(String msg, T data) {
        return of("200-0", msg, data);
    }

    public static <T> RespData<T> of(String resultCode, String msg) {
        return of(resultCode, msg, (T) new Empty());
    }

    public static <T> RespData<T> of(String resultCode, String msg, T data) {
        int statusCode = Integer.parseInt(resultCode.split("-", 2)[0]);

        return new RespData<>(resultCode, statusCode, msg, data);
    }

    @NonNull
    @JsonIgnore
    public boolean isSuccess() {
        return getStatusCode() >= 200 && getStatusCode() < 400;
    }

    @NonNull
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

    public <T> RespData<T> newDataOf(T data) {
        return new RespData<>(resultCode, statusCode, msg, data);
    }
}