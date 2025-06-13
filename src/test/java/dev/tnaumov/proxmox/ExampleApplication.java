package dev.tnaumov.proxmox;

import dev.tnaumov.proxmox.client.PveClient;
import dev.tnaumov.proxmox.service.PveVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("example")
@SpringBootApplication
public class ExampleApplication {

    private static final Logger log = LoggerFactory.getLogger(ExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PveClient pveClient, PveVersionService versionService) {
        return args -> {
            log.info("Root API: {}", pveClient.get("/", String.class));
            log.info("PVE version: {}", versionService.getVersion().data());
        };

    }

}
