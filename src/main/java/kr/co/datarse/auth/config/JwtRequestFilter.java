package kr.co.datarse.auth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import kr.co.datarse.auth.service.JwtUserDetailsService;
import kr.co.datarse.auth.util.JwtTokenUtil;
import kr.co.datarse.exception.EmailDuplicateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 유입토큰정보
		String requestTokenHeader = request.getHeader("Authorization");
		log.info("requestTokenHeader : " + requestTokenHeader);
		
		// client ip 획득
		String clientIp = request.getHeader("X-Forwarded-For");
		log.info("clientIp : " + clientIp);
//		String jSessionId = request.getRequestedSessionId();
		
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				
			} catch (IllegalArgumentException ex){
                log.error("Unable to get JWT token", ex);
            } catch (Exception ex) {
                log.error("token valid error:" + ex.getMessage() ,ex);
                throw new RuntimeException("11 Username from token error");
            }
		} else {
			log.warn("JWT token does not begin with Bearer String");
		}
		
		// 토큰 수집 후 검증
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				// header token 정보 설정
				response.setHeader("Authorization", jwtToken);
				
				// context에 인증 설정 후 현재 사용자가 인증되도록 지정 (spring security 설정이 성공적으로 넘어감)
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(jwtToken);
				request.setAttribute("error", "qwfqfqwjpof");
				dispatcher.forward(request, response);
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
