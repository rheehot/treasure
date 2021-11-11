package com.shop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @ControllerAdvice : 컨트롤러 전역에서 발생할 수 있는 예외를 잡아준다., @ResponseBody가 적용된 어노테이션.
@Slf4j
public class GlobalExceptionHandler {
    /**
     * ResponseEntity<T> : HTTP Request에 대한 응답 데이터를 포함하는 클래스로
     * <Type>에 해당하는 데이터와 HTTP 상태 코드를 함께 리턴할 수 있다.
     */

    @ExceptionHandler(RuntimeException.class) //특정 클래스에서 발생할 수 있는 예외를 잡는다.
    public String handleRuntimeException(final RuntimeException e) {
        log.error("handleRuntimeException : {}", e.getMessage());
        return e.getMessage();
    }

    //Developer Custom Exception
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        log.error("handleCustomException : {}", e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    //HTTP 405 Exception
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException : {}", e.getMessage());
        return ResponseEntity.status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }

    //HTTP 500 Exception
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException : {}", e.getMessage());
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
