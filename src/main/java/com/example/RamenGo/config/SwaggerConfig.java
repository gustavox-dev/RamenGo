package com.example.RamenGo.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ramenGO!")
                        .version("1.0")
                        .description("This API allows users to list available broths, available proteins and place an order."))
                .externalDocs(new   ExternalDocumentation()
                        .description("Documentação do Projeto RamenGo")
                        .url("https://github.com/example/RamenGo"));
    }
}