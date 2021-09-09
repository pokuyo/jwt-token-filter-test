package kr.co.datarse.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignVo {
	String userid;
	String password;
	String username;
	String jsessionid;
	String hashkey;
}
