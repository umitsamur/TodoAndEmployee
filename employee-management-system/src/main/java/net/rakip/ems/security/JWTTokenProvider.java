package net.rakip.ems.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.joining;

@Slf4j
@Component
public class JWTTokenProvider {

	@Value("${app.jwt-secret}")
	private String JWT_SECRET;
	
	@Value("${app.jwt-expiration-miliseconds}")
	private long JWT_EXPIRATION;
	
	//Generate JWT Token
	public String generateToken(Authentication authentication) {
		
		String username = authentication.getName();
	
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		ClaimsBuilder claimsBuilder = Jwts.claims().subject(username);
		
		if (!authorities.isEmpty()) {
			claimsBuilder.add("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
		}
		
		
		Claims claims = claimsBuilder.build();
		
		
		Date currentDate = new Date();
		
		Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);		
		
		String token = Jwts.builder()
				.claims(claims)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key())
                .compact();
		
		log.info("\nJWTTokenProvider class");
		log.info("jwtSecret: " + JWT_SECRET + ", jwtExpiration: " + JWT_EXPIRATION);
		log.info("generateToken()->username: " + username);
		log.info("generateToken()->getPrincipal(): " + authentication.getPrincipal().toString());
		log.info("generateToken()->authorities(): ");
		authorities.forEach(authority -> log.info(authority.getAuthority()));
		log.info("generateToken()-> claimsBuilder: " + claimsBuilder.toString());
		log.info("\nif");
		log.info("generateToken()-> claimsBuilder: " + claimsBuilder.toString());
		log.info("generateToken()-> claims: " + claims.toString());
		log.info("getJWTToken()->expireDate: " + expireDate);
		log.info("token: " + token);
		return token;
		
	}
	
	
	private Key key(){
		log.info("keys: " + Decoders.BASE64.decode(JWT_SECRET));
        return Keys.hmacShaKeyFor(
        		Decoders.BASE64.decode(JWT_SECRET)
        );
    }
	
	
	// get username from jwt token
	public String getUsername(String token) {
		Claims claims = Jwts.parser()
				.verifyWith((SecretKey) key())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		String username = claims.getSubject();
		return username;
	}
	
	
	
	// Validate JWT Token
	public boolean validateToken(String token) {
		
		try {
			Jws<Claims> claims = Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token);
			log.info("validateToken -> claims: " + claims);
			 //  parseClaimsJws will check expiration date. No need do here.
	        log.info("expiration date: {}", claims.getPayload().getExpiration());
	        log.info("validateToken(): true");
	        return true;
		} catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        }
		log.info("validateToken(): false");
		return false;
	}
	
	
	
	
}
