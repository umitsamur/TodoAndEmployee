package net.rakip.ems.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoAPIException extends RuntimeException {

	private HttpStatus status;
	private String message;
	
	
}

/*

@Getter
public class TodoAPIException extends ResponseStatusException {

	public TodoAPIException(HttpStatusCode status, String message) {
		super(status,message,null);
	}

}

*/