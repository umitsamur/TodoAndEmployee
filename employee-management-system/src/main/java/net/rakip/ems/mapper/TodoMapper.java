package net.rakip.ems.mapper;

import net.rakip.ems.dto.TodoDTO;
import net.rakip.ems.entity.Todo;

public class TodoMapper {
	
	public static TodoDTO mapToTodoDTO(Todo todo) {
		return new TodoDTO(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted());
	}
	
	public static Todo mapToTodo(TodoDTO todoDTO) {
		return new Todo(todoDTO.getId(), todoDTO.getTitle(), todoDTO.getDescription(), todoDTO.isCompleted());
	}

}
