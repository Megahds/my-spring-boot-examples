package com.megah.springsecurityjwt.jwtutils;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2992341170036873251L;

	public static final long TOKEN_VALIDITY = 10 * 60 * 60;

//	@Value("${secret}")
//	private String jwtSecret;
	
	private Key jwtSecret = SecretKey.key;
	
	public String generateJwtToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(jwtSecret, SignatureAlgorithm.HS512).compact();
	}

	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		
		String username = getUsernameFromToken(token);
//		Claims claims = ((JwtParser) Jwts.parserBuilder().setSigningKey(jwtSecret)).parseClaimsJws(token).getBody();
		final Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		
//		final Claims claims = ((JwtParser) Jwts.parserBuilder().setSigningKey(jwtSecret)).parseClaimsJws(token).getBody();
		
		final Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
}
