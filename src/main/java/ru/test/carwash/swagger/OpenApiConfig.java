package ru.test.carwash.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Carwash Application",
                description = "Программа для автомойки", version = "1.0.0",
                contact = @Contact(
                        name = "Kharina Marina",
                        email = "m_e_kharina@mail.ru"
                )
        )
)
public class OpenApiConfig {

}
