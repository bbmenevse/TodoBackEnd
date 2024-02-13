package com.example.springjpajdbc.jdbc.todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
//Disabled filters
//I searched online to check but I am not sure if its better to disable filters or create a class with @TestConfiguration and mock the jwt.

@AutoConfigureMockMvc(addFilters = false)
class TodoResourceTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	
	
	@Test
	public void testBasicAuthURL() throws Exception {
		String userName = "testUser";
		mockMvc.perform(get("/api/login/{userName}", userName))
		.andExpect(status().isOk())
		.andExpect(content().string("Success"));
	   }
	
}
