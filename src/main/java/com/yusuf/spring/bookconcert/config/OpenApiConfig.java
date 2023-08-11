package com.yusuf.spring.bookconcert.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Concert Ticket Reservation API")
                        .description("API documentation for concert ticket reservation service")
                        .version("1.0"));
    }

}
