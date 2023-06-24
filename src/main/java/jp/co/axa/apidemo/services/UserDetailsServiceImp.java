package jp.co.axa.apidemo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.constants.ErrorCode;
import jp.co.axa.apidemo.entities.UserData;
import jp.co.axa.apidemo.exception.ApiException;
import jp.co.axa.apidemo.repositories.UserDataRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	   @Autowired
	    private UserDataRepository userDataRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userDataRepo.findByUsername(username);

           if (userData != null) {
                Collection<String> mappedAuthorities = Arrays.asList(userData.getRole().split(","));
                User user = new User(username, new BCryptPasswordEncoder().encode(userData.getPassword()), mappedAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                return user;
            
        } else {
            throw new ApiException(Constants.NOT_FOUND, ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
