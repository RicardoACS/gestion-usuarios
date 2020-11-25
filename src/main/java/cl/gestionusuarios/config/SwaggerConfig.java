package cl.gestionusuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

/**
 * Configuración de Swagger
 *
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerConfig(){
        return new OpenAPI()
                .info(new Info()
                .title("Gestión de usuarios")
                .version("1.0")
                .description("Gestiona a los usuarios"));
    }
}
