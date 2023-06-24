package jp.co.axa.apidemo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.axa.apidemo.config.TokenProvider;
import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.constants.ErrorCode;
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
		UserData request = new UserData();
		request.setPassword("password");
		request.setUsername("axaUser");
		request.setRole("ADMIN");
		UserDetails userDetails = createUserDetails("axaUser", "password", "ROLE_ADMIN");
		Mockito.when(userDetailsService.loadUserByUsername("axaUser")).thenReturn(userDetails);
		Assert.assertNotNull(loginController.login(request));
	}

	@Test
	public void testLoginException1() {
		UserData request = new UserData();
		request.setPassword("pass123");
		request.setUsername("otherAxa");
		request.setRole("ADMIN");
		Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

		ApiException e = assertThrows(ApiException.class, () -> loginController.login(request));
		Assertions.assertEquals(Constants.INVALID_CREDENTIALS, e.getMessage());
	}

	@Test
	public void testLoginException2() {
		UserData request = new UserData();
		request.setPassword("pass123");
		request.setUsername("otherAxa");
		request.setRole("ADMIN");
		Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(DisabledException.class);

		ApiException e = assertThrows(ApiException.class, () -> loginController.login(request));
		Assertions.assertEquals(Constants.USER_DISABLED, e.getMessage());

	}

	private UserDetails createUserDetails(String username, String password, String... roles) {
		List<SimpleGrantedAuthority> authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new).toList();
		return new User(username, password, authorities);
	}

}
