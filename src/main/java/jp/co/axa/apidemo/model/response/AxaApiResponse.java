package jp.co.axa.apidemo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AxaApiResponse<T> {

	private static final long serialVersionUID = -8091879091924046844L;

    private HttpStatus status;
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String errorCode) {
        this.code = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
