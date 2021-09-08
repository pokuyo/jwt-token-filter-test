package kr.co.datarse.User;

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

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/api/user/regist")
	@ResponseBody
	public ResponseEntity<ApiResponse> saveUser(@RequestBody SignVo signVo) {
		User user = User.builder()
						.email(signVo.getEmail())
						.password(signVo.getPassword())
						.name(signVo.getName())
						.hashkey(signVo.getHashkey())
						.build();
		userService.save(user);
		return new ResponseEntity<>(new ApiResponse(), HttpStatus.OK);
	}
	
	@GetMapping("/api/user/exist_user/{email}")
	public boolean findUserByEmail(@PathVariable String email) {
		Optional<User> user = userService.findUserByEmail(email);
		return user.isPresent() ? true : false;
	}
}
