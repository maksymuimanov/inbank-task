package io.inbank.task.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for the Loan Decision Engine API.
 * <p>
 * Configures the API documentation including title, version, description,
 * and server information for Swagger UI generation.
 *
 * @see io.inbank.task.controller.LoanDecisionController
 */
@Configuration
public class OpenAPIConfig {
    /**
     * Creates and configures the OpenAPI documentation bean.
     *
     * @return configured OpenAPI instance with API metadata
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Decision Engine API")
                        .version("1.0")
                        .description("REST API for deciding if a loan should be approved or rejected")
                )
                .addServersItem(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local server")
                );
    }
}
