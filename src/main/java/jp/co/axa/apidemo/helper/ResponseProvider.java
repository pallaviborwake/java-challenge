package jp.co.axa.apidemo.helper;


import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.constants.ErrorCode;
import jp.co.axa.apidemo.model.response.AxaApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseProvider {

    public static ResponseEntity<?> success(Object data) {

        AxaApiResponse apiResponse = new AxaApiResponse<>();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setCode(ErrorCode.SUCCESS.getDescription());
        apiResponse.setData(data);

        return new ResponseEntity<AxaApiResponse<?>>(apiResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> fail(HttpStatus status, String errorCode, String message) {

        AxaApiResponse response = new AxaApiResponse();
        response.setStatus(status);
        response.setCode(errorCode);
        response.setMessage(message);
        return new ResponseEntity<>(response, status);

    }
}