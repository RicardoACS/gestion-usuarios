package cl.gestionusuarios.util.validator;

import cl.gestionusuarios.dto.CreateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Configuration
@Slf4j
public class UserValidator {

    private String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private String regexPassword = "^(?=.*[a-z])[a-z]*[A-Z][a-z]*([0-9]{2})$";

    public List<String> validate(CreateUserDto request) {
        log.info("[Validator] Se inicia validador del usuario");
        List<String> errores = new ArrayList<>();

        if (!request.getEmail().matches(regexEmail)) {
            log.error("[validator] Email invalido");
            errores.add("Debe ingresar un email valido");
        }

        if (!request.getPassword().matches(regexPassword)) {
            log.error("[validator] Password invalida");
            errores.add("Debe ingresar una contraseña valida (Mayúscula, minúsculas y dos números)");
        }

        return errores;
    }

}
