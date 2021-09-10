package kr.co.datarse.auth.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.datarse.util.RSAUtil;

//@Component
@Service
public class JwtTokenUtil implements Serializable {
	
	private static final long serialVersionUID = -798416586417070603L;
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
    @Value("${jwt.secret}")
    private String secret;
    
    /**
     * 토큰에서 userName 검색
     * @param token
     * @return
     * @throws Exception 
     */
    public String getUsernameFromToken(String token) {
    	try {
    		return getClaimFromToken(token, Claims::getSubject);
    	} catch (Exception e) {
//			throw new UsernameFromTokenException("username from token exception");
    		System.out.println("username from token exception");
    		return null;
		}
    }
    
    /**
     * 토큰 만료날짜 검색
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
    	return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    	final Claims claims = getAllClaimsFromToken(token);
    	return claimsResolver.apply(claims);
    }
    
    
    /**
     * secret 키로 토큰 정보 검색
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
    	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    /**
     * 토큰만료체크
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
    	final Date expiration = getExpirationDateFromToken(token);
    	return expiration.before(new Date());
    }
    
    /**
     * 유저이름으로 토큰 생성
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
    	Map<String, Object> claims = new HashMap<>();
    	claims.put("usr_id", userDetails.getUsername());
    	return doGenerateToken(claims, userDetails.getUsername());
    }
    
    
    /**
     * 토큰생성
     * 1. setHeaderParam()	: header 설정
     * 2. setClaims()		: 토큰, issuer, expiration, subject, ID로 claims 정의
     * 3. setSubject()		: 토큰제목 설정
     * 4. setIssuedAt()		: 토큰발생시간
     * 5. setExpiration()	: 토큰유효기간
     * 6. signWith()		: HS512 알고리즘 + secret key로 JWT 서명
     * 4. compact()			: JWS compact Serialization : JWT를 URL 안전 문자열로 압축
     * @param claims
     * @param username
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String username) {
    	return Jwts.builder()
    				.setHeaderParam("typ", "JWT")
    				.setClaims(claims)
    				.setSubject(username)
    				.setIssuedAt(new Date(System.currentTimeMillis()))
    				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
    				.signWith(SignatureAlgorithm.HS512, secret)
    				.compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
    	final String username = getUsernameFromToken(token);
    	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
