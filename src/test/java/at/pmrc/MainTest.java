package at.pmrc;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainTest {

	public static void main(String[] args) {
		SpringApplication.from(Application::main)
				.with(MainTest.class)
				.run(args);
	}
}