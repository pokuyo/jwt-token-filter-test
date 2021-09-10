package kr.co.datarse.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity(name = "cmm_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

	@Id
	@Column(unique = true)
	@NotEmpty
	private Number	usr_no;
	private String	usr_id;
	private String	usr_pw;
	private String	usr_nm;
	private String	pw_initl_yn;
	private Date	pw_initl_dt;
	private int		pw_failure_count;
	private String	cnt_frs;
	private String	cnt_mdl;
	private String	cnt_end;
	private String	role_cd;
	private String	gp_cd;
	private Number	register;
	private Date	rgsde;
	private String	updusr;
	private Date	updde;
	private String	lock_yn;
	private String	approval_yn;
	private String	usr_email;
	private String	offm_telno; 
	private String	usr_rspofc;
	
}
