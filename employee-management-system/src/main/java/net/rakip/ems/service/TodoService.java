package net.rakip.ems.service;

import java.util.List;

import net.rakip.ems.dto.TodoDTO;

public interface TodoService {

	TodoDTO addTodo(TodoDTO todoDTO);
	
	TodoDTO getTodoById(Long id);
	
	List<TodoDTO> getTodos();
	
	TodoDTO updateTodo(Long id, TodoDTO todoToBeUpdated);
	
	void deleteTodo(Long id);
	
	TodoDTO completeTodo(Long id);
	
	TodoDTO inCompeteTodo(Long id);
	
}
