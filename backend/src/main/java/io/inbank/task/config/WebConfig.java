package io.inbank.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for controllers used by the application.
 *
 * @see io.inbank.task.controller.LoanDecisionController
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String DEFAULT_API_VERSION = "1.0";
    public static final int DEFAULT_PATH_SEGMENT_INDEX = 1;


    /**
     * Configures API versioning for the application.
     *
     * @param configurer the API version configurer to customize
     */
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.addSupportedVersions(DEFAULT_API_VERSION)
                .setDefaultVersion(DEFAULT_API_VERSION)
                .usePathSegment(DEFAULT_PATH_SEGMENT_INDEX);
    }
}
