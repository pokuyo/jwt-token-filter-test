package kr.co.datarse.auth.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.datarse.user.model.CustomUserDetails;
import kr.co.datarse.user.model.UserModel;
import kr.co.datarse.user.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	PasswordEncoder encoder;
	
	/**
	 * userId로 유저정보 호출
	 */
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Optional<UserModel> userOptional = userService.findUserByUserId(userid);
		
		UserModel user = userOptional.orElseThrow(()->new UsernameNotFoundException("user name not found"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsr_id(), user.getUsr_pw(), new ArrayList<>());
	}
	
	/**
	 * TEST method
	 * @param userid
	 * @return
	 * @throws UsernameNotFoundException
	 */
	public UserDetails loadUserByUserid(String userid) throws UsernameNotFoundException {
		Optional<UserModel> userOptional = userService.findUserByUserId(userid);
		
		UserModel user = userOptional.orElseThrow(()->new UsernameNotFoundException("user name not found"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsr_id(), user.getUsr_pw(), new ArrayList<>());
	}
}
