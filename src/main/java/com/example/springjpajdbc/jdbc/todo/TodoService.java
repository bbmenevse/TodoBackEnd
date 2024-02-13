package com.example.springjpajdbc.jdbc.todo;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

public interface TodoService {
	
	public void deleteById(Long id) throws Exception;
	
	public List<Todo> findByUsername(String username);
	
	public void createTodo(String username, String description, LocalDate targetDate, boolean done);
	
	public void addTodo(Todo todo, String userName) throws Exception;
	
	public Todo findById(Long id) throws Exception;
	
	public void updateTodo(@Valid Todo todo) throws Exception;

	public List<Todo> findAll();

	public void save(@Valid Todo todo) throws Exception;
	
	
	
	
	

}
