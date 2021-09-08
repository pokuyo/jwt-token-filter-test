package kr.co.datarse.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.datarse.util.SHA512;

@Service
public class UserService {
	
	UserRepository repository;
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void save(User user) {
		Optional<User> aleadyUser = repository.findByEmail(user.getEmail());
		if(aleadyUser.isPresent()) {
			//throw new EmailDuplicateException("email duplicated",ErrorCode.EMAIL_DUPLICATION);
			System.out.println("UserService.save() Exception");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 일회성 hashkey 생성 (salt + user email)
		String hashkey = SHA512.getSHA512(user.getEmail());
		user.setHashkey(hashkey);
		
		repository.save(user);
	}
	
	public Optional<User> findUserByEmail(String email) {
		Optional<User> aleadyUser = repository.findByEmail(email);
		return aleadyUser;
	}
}
