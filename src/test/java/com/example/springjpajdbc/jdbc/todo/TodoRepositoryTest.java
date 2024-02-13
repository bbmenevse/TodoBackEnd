package com.example.springjpajdbc.jdbc.todo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
@DataJpaTest
@ActiveProfiles("test")
class TodoRepositoryTest {
	
	@Autowired
	TodoRepository todoRepository;
	
	LocalDate localDate = LocalDate.now();
	
	@BeforeAll
	public void setUp()
	{
		
		Todo todo1= new Todo();
		todo1.setId(1L);
		todo1.setDescription("Random Descipriton 1");
		todo1.setDone(false);
		todo1.setUsername("User One");
		todo1.setTargetDate(localDate);
		
		Todo todo2= new Todo();
		todo2.setId(2L);
		todo2.setDescription("Random Descipriton 2");
		todo2.setDone(false);
		todo2.setUsername("User Two");
		todo2.setTargetDate(localDate);
		
		Todo todo3= new Todo();
		todo3.setId(3L);
		todo3.setDescription("Random Descipriton 3");
		todo3.setDone(false);
		todo3.setUsername("User Three");
		todo3.setTargetDate(localDate);
		
		todoRepository.save(todo1);
		todoRepository.save(todo2);
		todoRepository.save(todo3);
		
	}

	@Test
	void todoCount() {	
		assertTrue(todoRepository.count()>2);
	}
	
	@Test
	void findTodo()
	{
		
		Todo todoTemp = todoRepository.findById(1L).orElse(null);
		
		if(todoTemp==null)
		{
			fail();
		}
	}
	
	

}
