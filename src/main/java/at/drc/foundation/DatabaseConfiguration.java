package at.drc.foundation;

import org.springframework.context.annotation.*;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.sql.*;
import javax.sql.*;
import java.util.concurrent.TimeUnit;

@Configuration
public class DatabaseConfiguration {
    private static final int MAX_ATTEMPTS = 5;
    private static final long DELAY_BETWEEN_ATTEMPTS_MS = 10000;

    @Bean
    @DependsOn("dockerComposerRunner")
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("postgres")
                .build();

        waitForDatabase(dataSource);

        return dataSource;
    }

    private void waitForDatabase(DataSource dataSource) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            try (Connection connection = dataSource.getConnection()) {
                if (connection.isValid(2)) {
                    System.out.println("Database is up and running!");
                    return;
                }
            } catch (SQLException e) {
                attempts++;
                System.out.println("Database is not yet ready. Attempt " + attempts + " of " + MAX_ATTEMPTS);
                try {
                    TimeUnit.MILLISECONDS.sleep(DELAY_BETWEEN_ATTEMPTS_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting for database to be ready", ie);
                }
            }
        }
        throw new RuntimeException("Unable to connect to the database after " + MAX_ATTEMPTS + " attempts");
    }
}