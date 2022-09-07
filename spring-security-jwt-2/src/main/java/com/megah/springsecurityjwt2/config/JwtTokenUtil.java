package com.megah.springsecurityjwt2.config;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4073158699047651084L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private static final Key secret_key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	@Value("${jwt.secret-key}")
	private String secret;

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Jwts.parserBuilder().setSigningKey(secret_key).build().parseClaimsJws(token).getBody();
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/*
	 * while creating the token - 1. Define claims of the token, like Issuer,
	 * Expiration, Subject, and the ID 2. Sign the JWT using the HS512 algorithm and
	 * secret key. 3. According to JWS Compact
	 * Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-
	 * 41#section-3.1) compaction of the JWT to a URL-safe string
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(secret_key, SignatureAlgorithm.HS512).compact();
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}
}
