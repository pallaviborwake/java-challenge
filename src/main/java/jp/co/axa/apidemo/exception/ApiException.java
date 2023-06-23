package jp.co.axa.apidemo.exception;

import org.springframework.http.HttpStatus;

import jp.co.axa.apidemo.constants.ErrorCode;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public ApiException(String message,ErrorCode errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
