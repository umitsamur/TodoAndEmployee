package net.rakip.ems.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import net.rakip.ems.dto.TodoDTO;
import net.rakip.ems.entity.Todo;
import net.rakip.ems.exception.ResourceNotFoundException;
import net.rakip.ems.repository.TodoRepository;
import net.rakip.ems.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	
	private ModelMapper modelMapper;
	
	public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public TodoDTO addTodo(TodoDTO todoDTO) {
		//Todo Mapper kullanarak
//		Todo todoToBeAdded = TodoMapper.mapToTodo(todoDTO);
//		Todo savedTodo = todoRepository.save(todoToBeAdded);
//		return TodoMapper.mapToTodoDTO(savedTodo);
		
		Todo todoToBeAdded = modelMapper.map(todoDTO, Todo.class);
		Todo savedTodo = todoRepository.save(todoToBeAdded);
		return modelMapper.map(savedTodo, TodoDTO.class);
	}
	
	@Override
	public TodoDTO getTodoById(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo is not exist with the given id: " + id));
		TodoDTO foundTodo = modelMapper.map(todo, TodoDTO.class);
		return foundTodo;
	}
	
	@Override
	public List<TodoDTO> getTodos(){
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map(todo -> modelMapper.map(todo, TodoDTO.class))
											.sorted(Comparator.comparing(TodoDTO::getId)).collect(Collectors.toList());
	}
	
	@Override
	public TodoDTO updateTodo(Long id, TodoDTO todoToBeUpdated) {
		Todo todo = todoRepository.findById(id)
										.orElseThrow(() -> new ResourceNotFoundException("Todo is not exist with the given id: " + id));
		todo.setTitle(todoToBeUpdated.getTitle());
		todo.setDescription(todoToBeUpdated.getDescription());
		todo.setCompleted(todoToBeUpdated.isCompleted());
		Todo updatedTodo = todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDTO.class);
	}
	
	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not exist with the given id: " + id));
		todoRepository.delete(todo);
	}
	
	@Override
	public TodoDTO completeTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not exist with the given id: " + id));
		todo.setCompleted(Boolean.TRUE);
		Todo completedTodo = todoRepository.save(todo);
		return modelMapper.map(completedTodo, TodoDTO.class);
	}
	
	@Override
	public TodoDTO inCompeteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not exist with the given id: " + id));
		todo.setCompleted(Boolean.FALSE);
		Todo completedTodo = todoRepository.save(todo);
		return modelMapper.map(completedTodo, TodoDTO.class);
	}
	
}
