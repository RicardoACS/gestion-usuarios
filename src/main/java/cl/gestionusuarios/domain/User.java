package cl.gestionusuarios.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Schema(description = "Identificador del usuario")
    @Id
    @Column(name = "user_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "created")
    private Date created;
    @Column(name = "modified")
    private Date modified;
    @Column(name = "lastLogin")
    private Date lastLogin;
    @Column(name = "token", length = 300)
    private String token;
    @Column(name = "isActive")
    private Boolean isActive;
}