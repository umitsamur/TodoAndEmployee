package net.rakip.ems.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.rakip.ems.entity.User;
import net.rakip.ems.exception.ResourceNotFoundException;
import net.rakip.ems.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist by Username or Email"));
		
		Set<GrantedAuthority> authorities = user.getRoles().stream()
												.map((role) -> new SimpleGrantedAuthority(role.getName()))
												.collect(Collectors.toSet());
		
		
		return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(), authorities);
	}
	
	
	
	
}
