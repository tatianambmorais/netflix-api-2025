package com.avengers.netflix.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI backendflixOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backendflix API")
                        .description("API para gerenciamento de usuários, filmes e assinaturas")
                        .version("v1.0"));
    }
}

