package cl.gestionusuarios.util.validator;

import cl.gestionusuarios.dto.CreateUserDto;
import cl.gestionusuarios.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Component
@Slf4j
public class UserValidator {

    private String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private String regexPassword = "^(?=.*[a-z])[a-z]*[A-Z][a-z]*([0-9]{2})$";


    public List<String> validate(CreateUserDto request) {
        log.info("[Validator] Se inicia validador del usuario");
        List<String> errors = new ArrayList<>();

        if (!request.getEmail().matches(regexEmail)) {
            errors.add("Debe ingresar un email válido");
        }

        if (!request.getPassword().matches(regexPassword)){
            errors.add("Debe ingresar una contraseña válida (Mayúscula, minúsculas y dos números)");
        }

        return errors;
    }

}
