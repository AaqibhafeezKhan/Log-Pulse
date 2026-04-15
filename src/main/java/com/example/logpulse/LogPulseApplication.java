
package com.example.logpulse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.alert.AlertRepository;

import java.util.List;

@SpringBootApplication
public class LogPulseApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogPulseApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadSampleData(AlertRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                    new Alert("Brute force SUCCESS: 192.168.1.50", "CRITICAL"),
                    new Alert("Brute force attempt: 10.0.0.12", "HIGH"),
                    new Alert("Suspicious Login: odd hour access for user admin", "HIGH"),
                    new Alert("Account Creation: new user test_account created", "MEDIUM"),
                    new Alert("Multi IP Login: user hr_manager from multiple locations", "CRITICAL")
                ));
            }
        };
    }
}
