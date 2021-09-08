package kr.co.datarse.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.co.datarse.auth.config.ExceptionHandlerFilter;
import kr.co.datarse.auth.config.JwtAuthenticationEntryPoint;
import kr.co.datarse.auth.config.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Autowired
	private ExceptionHandlerFilter exceptionHandlerFilter;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 일치하는 자격증명을 위해 사용자를 로드할 위치를 알수 있도록
        // AuthenticationManager를 구성한다.
        // BCryptPasswordEncoder를 이용
		auth.userDetailsService(jwtUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
		// return new StandardPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable()
			.csrf().disable()
			.authorizeRequests().antMatchers(
				 "/authenticate"
				,"/api/user/regist"
				,"/api/user/siginin"
			 )
			.permitAll()
			// 다른 모든 요청은 인증처리
			.anyRequest().authenticated()
			.and()
				// 상태없는 세션 이용, 세션에 사용자 정보 저장안함
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.formLogin().disable()
				.headers().frameOptions().disable();
		
		// 모든 요청에 토큰 검증 필터 추가
		http.addFilterBefore(jwtRequestFilter		, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(exceptionHandlerFilter	, JwtRequestFilter.class);
	}
}