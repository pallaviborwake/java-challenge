package jp.co.axa.apidemo.exception;

import jp.co.axa.apidemo.constants.ErrorCode;

public class EmployeeServiceException extends RuntimeException {
    private ErrorCode errorCode;

    public EmployeeServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
