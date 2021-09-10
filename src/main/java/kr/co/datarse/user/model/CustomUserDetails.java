package kr.co.datarse.user.model;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {

	String getUsr_id();
}
