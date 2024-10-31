package at.pmrc;

import org.springframework.boot.SpringApplication;

public class TestPostgresMongoRuntimeComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.from(PostgresMongoRuntimeComparisonApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
