package at.pmrc.foundation;

import jakarta.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class DockerComposerRunner {

    private static final String DOCKER_COMPOSE_FILE_PATH = "src/main/resources/docker/compose.yaml";

    @PreDestroy
    public void stopDockerContainers() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("docker-compose", "-f", DOCKER_COMPOSE_FILE_PATH, "down");
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("MongoDB, Mongo Express and Postgres containers stopped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void startDockerContainers() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("docker-compose", "-f", DOCKER_COMPOSE_FILE_PATH, "up", "-d");
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("MongoDB, Mongo Express and Postgres containers started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}