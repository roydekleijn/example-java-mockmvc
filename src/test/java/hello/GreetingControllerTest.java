package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class GreetingControllerTest {

	private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();

	@Test
	public void getBasicGreeting() throws Exception {
		mockMvc.perform(get("/greeting")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(
				jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void getPersonalizedGreeting() throws Exception {
		mockMvc.perform(get("/greeting").param("name", "Tester"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.content").value("Hello, Tester!"));
	}
}