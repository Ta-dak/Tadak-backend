package com.example.tadak.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@SecurityScheme(type = SecuritySchemeType.APIKEY,
        name = "Authorization",
        in = SecuritySchemeIn.HEADER)
public class SpringDocConfig {
    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .title("TADAK API")
                .version("1.0")
                .description("TADAK Server")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("Tadak").url("https://github.com/Ta-dak/Tadak-backend").email("raae7742@gmail.com"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .security(Collections.singletonList(new SecurityRequirement().addList("Authorization")));
    }
}
