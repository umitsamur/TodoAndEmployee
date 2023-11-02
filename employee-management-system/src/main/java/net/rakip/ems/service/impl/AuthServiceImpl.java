package net.rakip.ems.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.rakip.ems.dto.LoginDTO;
import net.rakip.ems.dto.RegisterDTO;
import net.rakip.ems.entity.Role;
import net.rakip.ems.entity.User;
import net.rakip.ems.exception.TodoAPIException;
import net.rakip.ems.exception.UserLoginException;
import net.rakip.ems.repository.RoleRepository;
import net.rakip.ems.repository.UserRepository;
import net.rakip.ems.security.JWTTokenProvider;
import net.rakip.ems.service.AuthService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JWTTokenProvider jwtTokenProvider;

	public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String register(RegisterDTO registerDTO) {

		// check username is already exists in database
		if (userRepository.existsByUsername(registerDTO.getUsername())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
		}

		// check email is already exists in database
		if (userRepository.existsByEmail(registerDTO.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
		}

		User user = new User();
		user.setName(registerDTO.getName());
		user.setUsername(registerDTO.getUsername());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);

		user.setRoles(roles);

		userRepository.save(user);

		return "User register succesfully!.";
	}

	@Override
	public String login(LoginDTO loginDTO) {
		Optional<User> user = userRepository.findByUsernameOrEmail(loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail());
		if (!user.isPresent()) {
			throw new UserLoginException(HttpStatus.UNAUTHORIZED, "Invalid Username or Email");
		}
		if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
			throw new UserLoginException(HttpStatus.UNAUTHORIZED, "Invalid password");
		}
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(
				loginDTO.getUsernameOrEmail(), loginDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		log.info("\nService Class: ");
		log.info("\nlogin.usernamePasswordAuthentication: " + usernamePasswordAuthentication);
		log.info("\nlogin.authentication: " + authentication);
		log.info("\nlogin.SecurityContextHolder.getContext(): " + SecurityContextHolder.getContext());
		
		// JWT ile ilgili olan kısım
		String token = jwtTokenProvider.generateToken(authentication);
		log.info("\nAuthService -> login.token: " + token );
		return token;
	}

}
