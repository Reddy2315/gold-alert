package com.goldalert.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Bean
    public OpenAPI goldAlertOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gold Price Alert API")
                        .description("API documentation for Gold Price Alert System")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Nagendharreddy Kondapu")
                                .email(emailFrom)
                        )
                );
    }
}
