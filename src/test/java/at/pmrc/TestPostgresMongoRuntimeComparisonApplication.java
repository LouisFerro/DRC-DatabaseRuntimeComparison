package at.pmrc;

import at.pmrc.Application;
import org.springframework.boot.SpringApplication;

public class TestPostgresMongoRuntimeComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(Application.class).run(args);
	}

}
