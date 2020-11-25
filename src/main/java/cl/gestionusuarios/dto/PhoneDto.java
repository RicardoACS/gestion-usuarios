package cl.gestionusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datos teléfonicos
 *
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDto {
    @Schema(description = "Número teléfonico del usuario")
    private String number;
    @Schema(description = "Código de la ciudad del teléfono del usuario")
    private String citycode;
    @Schema(description = "Código del país del teléfono del usuario")
    private String countrycode;
}