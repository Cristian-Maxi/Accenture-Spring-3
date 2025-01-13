package com.mindhub.todolist.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    Contact contact = new Contact().name("Cristian Gomez") .url("https://github.com/Cristian-Maxi/Accenture-Spring-3");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Server devServer = new Server().
            url("http://localhost:8080").
            description("Server URL in Development environment");

    Info info = new Info()
            .title("ToDo List")
            .version("1.0")
            .contact(contact)
            .description("Esta API contiene endpoints para una aplicación de Tareas.").termsOfService("https://www.bezkoder.com/terms")
            .license(mitLicense);
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )).info(info).servers(List.of(devServer));
    }
}
