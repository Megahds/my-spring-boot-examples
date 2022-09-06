package com.megah.springsecurityjwt.jwtutils;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class SecretKey {

	public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
