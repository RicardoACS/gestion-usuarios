package cl.gestionusuarios.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "phone")
public class Phone {
    @Schema(description = "Identificador del Teléfono")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;
    @Schema(description = "Número teléfonico del usuario")
    @Column(name = "number")
    private String number;
    @Schema(description = "Código de la ciudad del teléfono del usuario")
    @Column(name = "citycode")
    private String citycode;
    @Schema(description = "Código del país del teléfono del usuario")
    @Column(name = "countrycode")
    private String countrycode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}