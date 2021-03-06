package kr.co.datarse.user.model;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
//@Entity(name = "cmm_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements UserDetails{

//	@Id
//	@Column(unique = true)
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usr_pw;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usr_id;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
