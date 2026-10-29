package io.emit.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Emit API")
                        .version("1.0.0")
                        .description("API REST do Emit - Plataforma de emissão de NF-e/NFS-e para MEIs")
                        .contact(new Contact().name("Emit Team"))
                        .license(new License().name("BSL 1.1")));
    }
}
