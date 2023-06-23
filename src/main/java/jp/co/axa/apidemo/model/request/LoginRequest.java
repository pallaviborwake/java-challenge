package jp.co.axa.apidemo.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("LoginRequest")
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @ApiModelProperty(name = "username", dataType = "String", required = true, example = "axaUser", position = 1)
    @NotBlank(message = "username is required")
    private String username;
    @ApiModelProperty(name = "Password", dataType = "String", required = true, example = "password", position = 2)
    @NotBlank(message = "password is required")
    private String password;

    public LoginRequest() {//need default constructor for JSON Parsing
    }

    public LoginRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
