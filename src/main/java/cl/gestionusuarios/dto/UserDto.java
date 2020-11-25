package cl.gestionusuarios.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Datos del usuario
 *
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Schema(description = "Identificador del usuario")
    private String id;
    @Schema(description = "Nombre del usuario")
    private String name;
    @Schema(description = "Email del usuario")
    private String email;
    @Schema(description = "Contraseña del usuario")
    @JsonIgnore
    private String password;
    @Schema(description = "Fecha de creación")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date created;
    @Schema(description = "Fecha de modificación")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modified;
    @JsonProperty("last_login")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date lastLogin;
    @Schema(description = "Token")
    private String token;
    @JsonProperty("isactive")
    private Boolean isActive;
    @Schema(description = "Teléfonos")
    private List<PhoneDto> phones;
}
