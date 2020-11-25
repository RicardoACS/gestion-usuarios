package cl.gestionusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request para crear el usuario
 *
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    @Schema(description = "Nombre del usuario")
    private String name;
    @Schema(description = "Email del usuario")
    private String email;
    @Schema(description = "Contraseña del usuario")
    private String password;
    @Schema(description = "Teléfonos")
    private List<PhoneDto> phones;
}