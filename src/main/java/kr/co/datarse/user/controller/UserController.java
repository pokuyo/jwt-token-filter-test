package kr.co.datarse.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.datarse.auth.util.ApiResponse;
import kr.co.datarse.user.model.SignVo;
import kr.co.datarse.user.model.User;
import kr.co.datarse.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/api/user/regist")
	@ResponseBody
	public ResponseEntity<ApiResponse> saveUser(@RequestBody SignVo signVo) {
		User user = User.builder()
						.userid(signVo.getUserid())
						.password(signVo.getPassword())
						.username(signVo.getUsername())
						.jsessionid(signVo.getJsessionid())
						.hashkey(signVo.getHashkey())
						.build();
		userService.save(user);
		return new ResponseEntity<>(new ApiResponse(), HttpStatus.OK);
	}
	
	@GetMapping("/api/user/exist_user/{email}")
	public boolean findUserByEmail(@PathVariable String userid) {
		Optional<User> user = userService.findUserByUserId(userid);
		return user.isPresent() ? true : false;
	}
}
