package com.experian.serasa.rest.api.infra.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Wiliender Ferreira Silva",
                        email = "wiliendersilva@gmail.com",
                        url = "https://github.com/wiliender"
                ),
                description = "OpenApi documentation for Srping Security",
                title = "OpenaApi specification - Wiliendedr",
                version = "1.0"
        ),
                servers = {
                    @Server(
                            description = "Local ENV",
                            url = "http://localhost:8080"
                    )
                },
                security = {
                    @SecurityRequirement(
                        name = "bearerAuth"
                    )
                }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
