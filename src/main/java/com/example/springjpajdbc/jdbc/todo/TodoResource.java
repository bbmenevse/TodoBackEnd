package com.example.springjpajdbc.jdbc.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpajdbc.jdbc.aspect.SimpleAnnotation;

@RestController
public class TodoResource {
	
	private TodoService todoService;
	
	
	public TodoResource(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@Autowired
	public TodoResource(TodoServiceImplementation todoService) {
		this.todoService=todoService;
	}
	
	@GetMapping("/api/{userName}/todos")
	public List<Todo> retrieveTodos(@PathVariable String userName) throws Exception
	{
		// Self note: Don't forget that this doesn't get initialized at class level since authentication by user is not yet done when the bean is created!
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// On Postman, The authenticated user can enter a name other then their own and this method will respond back
		// acording to the fake userAdress, So I call on Authentication to get the userAddress directly.
	    String emailAdress= authentication.getName();
	    
	    if(userName.equals(emailAdress))
	    {
	    	return todoService.findByUsername(emailAdress);
	    }
	    else
	    {
	    	throw new Exception("The user's mail address doesn't match the one that's stored!");
	    }
	}
	
	@GetMapping("/api/login/{userName}")
	public String basicAuthURL(@PathVariable String userName)
	{
		return "Success";
	}
	
	@GetMapping("/welcome/{userName}")
	@SimpleAnnotation
	public String welcomeApi(@PathVariable String userName)
	{
		
		// This is added to check the time this method call takes.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Welcome";
	}
	
	@GetMapping("/api/{userName}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String userName, @PathVariable Long id)
	{
		try {
			return todoService.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("/api/{userName}/todos/{id}")
	public ResponseEntity<Void> updateTodo(@PathVariable String userName, @PathVariable int id,@RequestBody Todo todo) throws Exception
	{
		System.out.println("Geldi");
		todoService.updateTodo(todo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/{userName}/todos/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable String userName, @PathVariable Long id)
	{
		try {
			todoService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was a problem with deleting the todo: "+ e);
		}
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping("/api/{userName}/todos")
	public ResponseEntity<Void> addTodo(@PathVariable String userName,@RequestBody Todo todo) throws Exception
	{
		todoService.addTodo(todo,userName);
		return ResponseEntity.noContent().build();
	}

}
