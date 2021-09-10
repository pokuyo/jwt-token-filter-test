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
import kr.co.datarse.user.model.UserModel;
import kr.co.datarse.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/api/user/regist")
	@ResponseBody
	public ResponseEntity<ApiResponse> saveUser(@RequestBody SignVo signVo) {
		UserModel user = UserModel.builder()
						.usr_no(signVo.getUsr_no())
						.usr_id(signVo.getUsr_id())
						.usr_pw(signVo.getUsr_pw())
						.usr_nm(signVo.getUsr_nm())
						.pw_initl_yn(signVo.getPw_initl_yn())
						.pw_initl_dt(signVo.getPw_initl_dt())
						.pw_failure_count(signVo.getPw_failure_count())
						.cnt_frs(signVo.getCnt_frs())
						.cnt_mdl(signVo.getCnt_mdl())
						.cnt_end(signVo.getCnt_end())
						.role_cd(signVo.getRole_cd())
						.gp_cd(signVo.getGp_cd())
						.register(signVo.getRegister())
						.rgsde(signVo.getRgsde())
						.updusr(signVo.getUpdusr())
						.updde(signVo.getUpdde())
						.lock_yn(signVo.getLock_yn())
						.approval_yn(signVo.getApproval_yn())
						.usr_email(signVo.getUsr_email())
						.offm_telno(signVo.getOffm_telno())
						.usr_rspofc(signVo.getUsr_rspofc())
						.build();
		userService.save(user);
		return new ResponseEntity<>(new ApiResponse(), HttpStatus.OK);
	}
	
	@GetMapping("/api/user/exist_user/{email}")
	public boolean findUserByEmail(@PathVariable String userid) {
		Optional<UserModel> user = userService.findUserByUserId(userid);
		return user.isPresent() ? true : false;
	}
}
