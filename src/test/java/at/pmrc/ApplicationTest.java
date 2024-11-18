package at.pmrc;

import org.springframework.boot.SpringApplication;

public class ApplicationTest {

	public static void main(String[] args) {
		SpringApplication.from(Application::main)
						 .with(Application.class)
						 .run(args);
	}
}