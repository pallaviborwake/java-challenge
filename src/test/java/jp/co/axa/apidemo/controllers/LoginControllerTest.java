package jp.co.axa.apidemo.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import jp.co.axa.apidemo.config.TokenProvider;
import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.entities.UserData;
import jp.co.axa.apidemo.exception.ApiException;
import jp.co.axa.apidemo.services.UserDetailsServiceImp;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;
	
	
	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private TokenProvider jwtTokenUtil;

	@Mock
	private UserDetailsServiceImp userDetailsService;
	
	
	@Test
	public void testLogin() {
		UserData request=new UserData();
		request.setPassword("pass123");
		request.setUsername("otherAxa");
		request.setRole("ADMIN");
		Assert.assertNotNull(loginController.login(request));

	}
	
	@Test
	public void testLoginException1() {
		UserData request=new UserData();
		request.setPassword("pass123");
		request.setUsername("otherAxa");
		request.setRole("ADMIN");
		Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

		try {
			loginController.login(request);

		}catch(ApiException e) {
			Assertions.assertEquals(Constants.INVALID_CREDENTIALS,e.getMessage());
		}
		
	}
		
		@Test
		public void testLoginException2() {
			UserData request=new UserData();
			request.setPassword("pass123");
			request.setUsername("otherAxa");
			request.setRole("ADMIN");
			Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(DisabledException.class);

			try {
				loginController.login(request);

			}catch(ApiException e) {
				Assertions.assertEquals(Constants.USER_DISABLED,e.getMessage());
			}

	}

}
