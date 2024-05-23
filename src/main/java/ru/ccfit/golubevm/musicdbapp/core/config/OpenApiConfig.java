package ru.ccfit.golubevm.musicdbapp.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Music db app", version = "1.0",
        description = "This application uses cookie-based auth. " +
                " To obtain cookie you need to login in service by path: /api/auth/login. " +
                "If you don't have an account, it's possible to register by path: /api/auth/register", contact = @Contact(name = "Golubev Maxim")),
        security = {@SecurityRequirement(name = "bearerToken"), @SecurityRequirement(name = "cookie")})
@SecurityScheme(name = "cookie", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "cookie")
public class OpenApiConfig {
}
