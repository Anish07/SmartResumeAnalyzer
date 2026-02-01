package com.smartresume.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.load();
			System.setProperty("GEMINI_API_KEY", dotenv.get("GEMINI_API_KEY"));
		} catch (Exception e) {
			System.out.println("Could not load .env file: " + e.getMessage());
		}
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public CommandLineRunner logConfig(Environment env) {
		return args -> {
			String url = env.getProperty("spring.datasource.url");
			System.out.println("DEBUG_CONFIG: URL=" + (url != null ? url.replaceAll(":[^:@/]+@", ":*****@") : "null"));
			String platform = env.getProperty("spring.jpa.database-platform");
			System.out.println("DEBUG_CONFIG: Platform=" + platform);
		};
	}
}
