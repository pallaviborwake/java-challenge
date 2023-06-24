package jp.co.axa.apidemo.model.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final String username;
    private final String role;
    
    public LoginResponse(String token,String username,String role) {
        this.token = token;
        this.username = username;
        this.role = role;

    }

    public String getToken() {
        return this.token;
    }
    
    public String getUsername() {
        return this.username;
    }
    public String getRole() {
        return this.role;
    }
}