package net.rakip.ems.configuration;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import net.rakip.ems.security.JWTAuthenticationEntryPoint;
import net.rakip.ems.security.JWTAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class ProjectConfiguration {
	
	private UserDetailsService userDetailsService;
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	public ProjectConfiguration(UserDetailsService userDetailsService, JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint, JWTAuthenticationFilter jwtAuthenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authorize) -> {
				/*authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
				authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
				authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
				authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
				authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");*/
				authorize.requestMatchers("/api/auth/**").permitAll();
				authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
				
				authorize.anyRequest().authenticated();
		})
			// Basic authentication --> application.properties dosyalarıyla
			// ya da
			// userDetailsService methodu kullanarak in-memory authentication oluşturma
			.httpBasic(Customizer.withDefaults()); // username: ata , password: 4505096
			
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails ata = User.builder()
//								.username("samur")
//								.password(passwordEncoder().encode("123456"))
//								.roles("USER")
//								.build();
//		UserDetails admin = User.builder()
//								.username("admin")
//								.password(passwordEncoder().encode("admin"))
//								.roles("ADMIN")
//								.build();
//		return new InMemoryUserDetailsManager(ata, admin);
//	}
	
	
	
	
	
}
