package tn.talan.academyApp.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.talan.academyApp.AcademyTestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AcademyTestApplication.class)
@AutoConfigureMockMvc
@Transactional
public class EventControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void searchEventsByNameAPI() throws Exception {

		mvc.perform(
				MockMvcRequestBuilders.get("/event/SearchEventsByName/{name}", "collab22")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

				
				

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

	
	
	
	
	
}
