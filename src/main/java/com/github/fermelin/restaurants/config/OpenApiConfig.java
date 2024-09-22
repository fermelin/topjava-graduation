package com.github.fermelin.restaurants.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@OpenAPIDefinition(info = @Info(title = "REST API documentation", version = "1.0", description = """
        Spring Boot based REST application by course <a href='https://topjava.ru/topjava'>Topjava</a>
        <p><b>Test credentials:</b><br>
        - user@gmail.com / password<br>
        - admin@gmail.com / admin<br>
        - guest@gmail.com / guest</p>
        """, contact = @Contact(url = "https://github.com/fermelin", name = "Vadim Borisov", email = "21vadimborisov@gmail.com")), security = @SecurityRequirement(name = "basicAuth"))
public class OpenApiConfig {

    static {
        var schema = new Schema<LocalTime>();
        schema.example(LocalTime.now().format(DateTimeFormatter.ISO_TIME));
        SpringDocUtils.getConfig().replaceWithSchema(LocalTime.class, schema);
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("REST API").pathsToMatch("/api/**").build();
    }
}
