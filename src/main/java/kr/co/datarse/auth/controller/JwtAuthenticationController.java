package kr.co.datarse.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.datarse.auth.model.JwtRequest;
import kr.co.datarse.auth.service.JwtUserDetailsService;
import kr.co.datarse.auth.util.ApiResponse;
import kr.co.datarse.auth.util.JwtTokenUtil;
import kr.co.datarse.user.model.CustomUserDetails;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@RequestMapping(value="/api/user/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsr_id(), authenticationRequest.getUsr_pw());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsr_id());
		final String token = jwtTokenUtil.generateToken(userDetails);
		// UserDetail Custom test
//		final CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUserid(authenticationRequest.getUsr_id());
//		final String token = jwtTokenUtil.generateToken(customUserDetails);
		
		ApiResponse response = new ApiResponse();
		// response.setData(token);
		
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);
		
//		return new ResponseEntity<>(response, HttpStatus.OK);
		return new ResponseEntity<>(response, header, HttpStatus.OK);
	}
	
	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch (DisabledException ex){
            throw new DisabledException("USER_DISABLED",ex);
        }catch (BadCredentialsException ex){
            throw new BadCredentialsException("INVALID_CREDENTIALS",ex);
        }
	}
}
