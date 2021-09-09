package kr.co.datarse.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.datarse.exception.EmailDuplicateException;
import kr.co.datarse.user.mapper.UserMapper;
import kr.co.datarse.user.model.User;
import kr.co.datarse.util.SHA512;

@Service
public class UserService {
	
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	private UserMapper userMapper;
	
	public void save(User user) {
		Optional<User> aleadyUser = userMapper.findByUserId(user);
		
		if(aleadyUser.isPresent()) {
			throw new EmailDuplicateException();
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 일회성 hashkey 생성 (salt + user email)
		String hashkey = SHA512.getSHA512(user.getUserid());
		user.setHashkey(hashkey);
		
		userMapper.registNewUser(user);
	}
	
	public Optional<User> findUserByUserId(String userid) {
		Optional<User> aleadyUser = userMapper.findByUserId(userid);
		return aleadyUser;
	}
	
}