package jp.co.axa.apidemo.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.axa.apidemo.constants.ErrorCode;
import jp.co.axa.apidemo.entities.UserData;
import jp.co.axa.apidemo.exception.ApiException;
import jp.co.axa.apidemo.repositories.UserDataRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImpTest {

	@InjectMocks
	UserDetailsServiceImp userDetailsServiceImp;
	@Mock
	 private UserDataRepository userDataRepo;
	
	
	@Test
	public void testLoadUserByUsername() {	
		 UserData userData=new  UserData ();
		 userData.setPassword("password");
		 userData.setUsername("axaUser");
		 userData.setRole("ADMIN");
		 Mockito.when(userDataRepo.findByUsername("axaUser")).thenReturn(userData);

		Assert.assertNotNull(userDetailsServiceImp.loadUserByUsername("axaUser"));
 
    }
	
	
	@Test
	public void testLoadUserByUsernameException() {				
		ApiException exception = assertThrows(ApiException.class,
                () -> userDetailsServiceImp.loadUserByUsername("axaUser"));
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
       
    }

}


