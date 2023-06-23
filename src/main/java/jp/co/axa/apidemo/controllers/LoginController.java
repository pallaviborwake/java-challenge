package jp.co.axa.apidemo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.axa.apidemo.config.TokenProvider;
import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.constants.ErrorCode;
import jp.co.axa.apidemo.entities.UserData;
import jp.co.axa.apidemo.exception.ApiException;
import jp.co.axa.apidemo.helper.ResponseProvider;
import jp.co.axa.apidemo.model.request.LoginRequest;
import jp.co.axa.apidemo.model.response.LoginResponse;
import jp.co.axa.apidemo.services.UserDetailsServiceImp;
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@Api(value = "Login Controller", tags = "Login Controller")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Invalid Credentials")})
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody @Valid UserData request) {

        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ApiException(Constants.USER_DISABLED, ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException e) {
            throw new ApiException(Constants.INVALID_CREDENTIALS, ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
    }
    
}
