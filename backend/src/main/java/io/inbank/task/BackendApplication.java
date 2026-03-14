package io.inbank.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Loan Decision Engine backend application.
 * <p>
 * This Spring Boot application provides REST APIs for evaluating loan requests
 * and making approval decisions based on customer credit segments.
 *
 * @see io.inbank.task.controller.LoanDecisionController
 */
@SpringBootApplication
public class BackendApplication {
    /**
     * Starts the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
