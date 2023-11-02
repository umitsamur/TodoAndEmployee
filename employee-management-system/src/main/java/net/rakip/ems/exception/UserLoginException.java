package net.rakip.ems.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginException extends RuntimeException{
	private HttpStatus status;
	private String message;
}
