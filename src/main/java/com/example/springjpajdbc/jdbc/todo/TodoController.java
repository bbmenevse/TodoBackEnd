package com.example.springjpajdbc.jdbc.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

			
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		
		
		String username = getLoggedInUsername(model);
		//List<Todo> todos = todoRepository.findByUsername(username);
		List<Todo> todos = todoService.findAll();
		System.out.println(todos);
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}

	//GET, POST
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(null, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		

		todoService.save(todo);
		
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam Long id) throws Exception {
		//Delete todo
		try {
			todoService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("The todo with the given id doesn't exist!");
		}
		return "redirect:list-todos";
		
	}

	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam Long id, ModelMap model) throws Exception {
		Todo todo;
		try {
			todo = todoService.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("The given id does not belong to any todo!");
		}
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoService.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	

}
