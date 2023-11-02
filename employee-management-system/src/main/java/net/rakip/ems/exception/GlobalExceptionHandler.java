package net.rakip.ems.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNoutFoundException(ResourceNotFoundException exception, WebRequest request){
		ErrorDetails details = new ErrorDetails(
				LocalDateTime.now(), 
				exception.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(details, exception.getStatus());
	}
	
	@ExceptionHandler(TodoAPIException.class)
	public ResponseEntity<ErrorDetails> handleTodoAPIException(TodoAPIException todoAPIException, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				todoAPIException.getMessage(),
				webRequest.getDescription(false)
				);
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserLoginException.class)
	public ResponseEntity<ErrorDetails> handleUserLoginException(UserLoginException userLoginException, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), userLoginException.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, userLoginException.getStatus());
	}
}
