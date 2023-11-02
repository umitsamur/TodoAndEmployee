package net.rakip.ems.service;

import net.rakip.ems.dto.LoginDTO;
import net.rakip.ems.dto.RegisterDTO;

public interface AuthService {

	String register(RegisterDTO registerDTO);
	
	String login(LoginDTO loginDTO);
	
	
}
