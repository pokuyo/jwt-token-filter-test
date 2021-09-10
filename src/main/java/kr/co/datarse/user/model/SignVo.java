package kr.co.datarse.user.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignVo {
//	String userid;
//	String password;
//	String username;
//	String jsessionid;
//	String hashkey;
	Number	usr_no;
	String	usr_id;
	String	usr_pw;
	String	usr_nm;
	String	pw_initl_yn;
	Date	pw_initl_dt;
	int		pw_failure_count;
	String	cnt_frs;
	String	cnt_mdl;
	String	cnt_end;
	String	role_cd;
	String	gp_cd;
	Number	register;
	Date	rgsde;
	String	updusr;
	Date	updde;
	String	lock_yn;
	String	approval_yn;
	String	usr_email;
	String	offm_telno; 
	String	usr_rspofc;
}
