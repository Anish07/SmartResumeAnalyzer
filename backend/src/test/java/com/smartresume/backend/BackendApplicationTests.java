package com.smartresume.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartresume.backend.controller.AuthController;
import com.smartresume.backend.model.User;
import com.smartresume.backend.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BackendApplicationTests {

	private MockMvc mockMvc;

	@Mock
	private AuthService authService;

	@InjectMocks
	private AuthController authController;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testLoginSuccess() throws Exception {
		// Arrange
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("password");

		Map<String, String> response = Map.of("token", "dummy-token");

		when(authService.login(any(User.class))).thenReturn(response);

		// Act & Assert
		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").value("dummy-token"));
	}

}
