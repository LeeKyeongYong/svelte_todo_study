package com.todostudy.myjob.global.exceptionHandlers;

import com.todostudy.myjob.global.https.ReqData;
import com.todostudy.myjob.global.https.RespData;
import com.todostudy.myjob.standard.base.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ReqData rq;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        // 어짜피 이 서버(스프링부트)를 API서버로만 이용할 것이므로 이 코드는 필요 없다.
        // 그리고 isApi 의 로직은 조금 더 보강을 해야 한다.
        // if (!rq.isApi()) throw ex;

        return handleApiException(ex);
    }

    // 자연스럽게 발생시킨 예외처리
    private ResponseEntity<Object> handleApiException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("resultCode", "500-0");
        body.put("statusCode", 500);
        body.put("msg", ex.getLocalizedMessage());

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        body.put("data", data);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        data.put("trace", sw.toString().replace("\t", "    ").split("\\r\\n"));

        String path = rq.getCurrentUrlPath();
        data.put("path", path);

        body.put("success", false);
        body.put("fail", true);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 개발자가 명시적으로 발생시킨 예외처리
    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 참고로 이 코드의 역할은 error 내용의 스키마를 타입스크립트화 하는데 있다.
    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RespData<Empty>> handle(GlobalException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getRsData().getStatusCode());
        rq.setStatusCode(ex.getRsData().getStatusCode());

        return new ResponseEntity<>(ex.getRsData(), status);
    }
}
