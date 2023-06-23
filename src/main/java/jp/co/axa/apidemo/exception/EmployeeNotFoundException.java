package jp.co.axa.apidemo.exception;

import org.springframework.http.HttpStatus;

import jp.co.axa.apidemo.constants.ErrorCode;

public class EmployeeNotFoundException extends EmployeeServiceException {
	private static final long serialVersionUID = 1L;

	
    public EmployeeNotFoundException(String message,ErrorCode e) {
		super(message, e);
	}

}
