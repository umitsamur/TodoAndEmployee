package net.rakip.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.rakip.ems.dto.JWTAuthResponse;
import net.rakip.ems.dto.LoginDTO;
import net.rakip.ems.dto.RegisterDTO;
import net.rakip.ems.service.AuthService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	// Build register REST API
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
		String response = authService.register(registerDTO);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO){
		//Basic
		//String response = authService.login(loginDTO);
		
		// jwt
		String token = authService.login(loginDTO);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.OK);
		
		// basic
		// return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
}
