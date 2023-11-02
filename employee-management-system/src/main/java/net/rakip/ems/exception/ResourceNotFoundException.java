package net.rakip.ems.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
	
	private HttpStatus status;
	private String message;
	
	public ResourceNotFoundException(String message) {
		this.status = HttpStatus.BAD_REQUEST;
		this.message = message;
	}
    
}
