package net.rakip.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.rakip.ems.dto.TodoDTO;
import net.rakip.ems.service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDto){
		TodoDTO addedTodo = todoService.addTodo(todoDto);
		return new ResponseEntity<TodoDTO>(addedTodo, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") Long todoId){
		TodoDTO todoDTO = todoService.getTodoById(todoId);
		return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<TodoDTO>> getTodos(){
		List<TodoDTO> todos = todoService.getTodos();
		
		return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDTO todoToBeUpdated){
		TodoDTO todoDTO = todoService.updateTodo(id, todoToBeUpdated);
		return new ResponseEntity<TodoDTO>(todoDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
		todoService.deleteTodo(id);
		return new ResponseEntity<String>("todo deleted succesfully: ", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long id){
		TodoDTO todoDTO = todoService.completeTodo(id);
		return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable("id") Long id){
		TodoDTO todoDTO = todoService.inCompeteTodo(id);
		return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK);
	}
	
	
}
