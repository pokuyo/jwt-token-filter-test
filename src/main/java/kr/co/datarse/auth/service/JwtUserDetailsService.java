package kr.co.datarse.auth.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.datarse.user.model.User;
import kr.co.datarse.user.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Optional<User> userOptional = userService.findUserByUserId(userid);
		
		User user = userOptional.orElseThrow(()->new UsernameNotFoundException("user name not found"));
		
		return new org.springframework.security.core.userdetails.User(user.getUserid(), user.getPassword(), new ArrayList<>());
	}
}
