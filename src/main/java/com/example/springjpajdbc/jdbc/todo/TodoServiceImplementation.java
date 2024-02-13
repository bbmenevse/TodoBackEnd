package com.example.springjpajdbc.jdbc.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoServiceImplementation implements TodoService {
	
	private TodoRepository todoRepository;
	
	
	
	public TodoServiceImplementation(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	
	LocalDate tempDate;
	
	@Override
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todoRepository.findByUsername(username).stream().filter(predicate).toList();
	}
	
	@Override
	public void createTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(null,username,description,targetDate,done);
		todoRepository.save(todo);
	}
	
	@Override
	public void addTodo(Todo todo, String userName) throws Exception
	{
		tempDate = LocalDate.now();
		
		if(todo==null)
		{
			throw new Exception("Cannot Create the Todo Object! It is null!");
		}
		
		else if(todo.getUsername().isEmpty())
		{
			throw new Exception("Cannot create a todo with no username!");
		}
		else if(!todo.getUsername().equals(userName))
		{
			throw new Exception("The sender and the todo object's user name does not match!");
		}
		else if(todo.getDescription().length()<5 || todo.getDescription().length()>155)
		{
			throw new Exception("Description length must be between 5 and 155(both included!)");
		}
		
		else if(todo.getTargetDate().isBefore(tempDate))
		{
			throw new Exception ("The Target date for finishing todo can not be before today's target!");
		}
		
		
		todoRepository.save(todo);
		
		
	
	}
	
	@Override
	public void deleteById(Long id) throws Exception{
		
			Optional<Todo> optionalTodo= todoRepository.findById(id);
			if(optionalTodo.isPresent())
			{
				todoRepository.deleteById(id);
			}
			else
			{
				throw new Exception("The todo object you are trying to delete doesn't exist!");
			}

		
	}

	@Override
	public Todo findById(Long id) throws Exception{
		Optional<Todo> optionalTodo= todoRepository.findById(id);
		if(optionalTodo.isPresent())
		{
			return optionalTodo.get();
		}
		else {
			throw new Exception("The given id does not belong to any todo!");
		}
		
	}
	
	@Override
	public void updateTodo(@Valid Todo todo) throws Exception {
		
		Optional<Todo> existingTodo= todoRepository.findById(todo.getId());
		
		if(existingTodo.isEmpty())
		{
			throw new Exception("The todo you are trying to update does not exist!");
		}
		
		Todo tempTodo=existingTodo.get();
		
		tempDate = LocalDate.now();
		System.out.println(tempDate);
		if(todo!=null)
		{
			// This is temporary check as I plan to add authentication for requests
			if(!todo.getUsername().equals(existingTodo.get().getUsername()))
			{
				throw new Exception("The sender's username and the old userName doesn't match!");
			}
			else if(todo.getDescription().length()<5)
			{
				throw new Exception("The description is too short!");
			}
			else if(todo.getDescription().length()>155)
			{
				throw new Exception("The description is too long!");
			}
			else if(todo.getTargetDate().isBefore(tempDate))
			{
				throw new Exception ("The Target date for finishing todo can not be before today's target!");
			}
		}
		
		//Should change to jpa in the future
		tempTodo.setDescription(todo.getDescription());
		tempTodo.setDone(todo.getDone());
		tempTodo.setTargetDate(todo.getTargetDate());
		todoRepository.save(tempTodo);
	}

	@Override
	public List<Todo> findAll() {
		
		//Not user spesific on purpose.
		//Plan to make the server side kinda admin page.
		return todoRepository.findAll();
	}

	@Override
	public void save(Todo todo) throws Exception
	{
		tempDate = LocalDate.now();
		
		if(todo==null)
		{
			throw new Exception("Cannot Create the Todo Object! It is null!");
		}
		
		else if(todo.getUsername().isEmpty())
		{
			throw new Exception("Cannot create a todo with no username!");
		}
		
		else if(todo.getDescription().length()<5 || todo.getDescription().length()>155)
		{
			throw new Exception("Description length must be between 5 and 155(both included!)");
		}
		
		else if(todo.getTargetDate().isBefore(tempDate))
		{
			throw new Exception ("The Target date for finishing todo can not be before today's target!");
		}
		
		todoRepository.save(todo);
		
		
	
	}

}
