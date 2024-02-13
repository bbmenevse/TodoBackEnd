package com.example.springjpajdbc.jdbc.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TodoRepository extends JpaRepository<Todo,Long>  {
	
	
	public List<Todo> findByUsername(String username);
	
	public List<Todo> findAll();
	
	public void deleteById(Long id);
	
	
	
	
	//@Query("select a from Todo a where ")
	//public Todo findTodosBetweenDate(LocalDate date1, LocalDate date2);
	
	

}
