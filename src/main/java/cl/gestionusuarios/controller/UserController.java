package cl.gestionusuarios.controller;

import cl.gestionusuarios.domain.User;
import cl.gestionusuarios.dto.CreateUserDto;
import cl.gestionusuarios.dto.UserDto;
import cl.gestionusuarios.service.UserService;
import cl.gestionusuarios.util.validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cl.gestionusuarios.util.Endpoint.USER;
import static cl.gestionusuarios.util.Endpoint.VERSION_1;
import static cl.gestionusuarios.util.ErrorMessageUtil.errorMessage;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Tag(name = "Usuario", description = "Controlador de los usuarios")
@RestController
@RequestMapping(VERSION_1 + USER)
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Operation(description = "Crea un usuario")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "409", description = "Ya existe el email")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto request) {
        log.info("[Controller] Se creará un usuario");
        List<String> errors = userValidator.validate(request);
        if (!errors.isEmpty()) {
            return new ResponseEntity(errorMessage(errors), HttpStatus.BAD_REQUEST);
        }

        return userService.createUser(request);
    }

    @Operation(description = "Modifica un usuario")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Error en la request")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id,
                                              @RequestBody CreateUserDto request) {
        log.info("[Controller] Se modificará un usuario");
        List<String> errors = userValidator.validate(request);
        if (!errors.isEmpty()) {
            return new ResponseEntity(errorMessage(errors), HttpStatus.BAD_REQUEST);
        }

        return userService.updateUser(id, request);
    }

    @Operation(description = "desactiva un usuario")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Error en la request")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @PutMapping("/{id}/change-state")
    public ResponseEntity<String> changeState(@PathVariable("id") String id,
                                              @RequestParam("state") Boolean state) {
        log.info("[Controller] Se desactivará un usuario");

        return userService.changeState(id, state);
    }

    @Operation(description = "Obtiene un usuario")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Error en la request")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id) {
        log.info("[Controller] Se buscará un usuario");

        return userService.getUserById(id);
    }

    @Operation(description = "Obtiene todos los usuarios")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        log.info("[Controller] Se obtendrán todos los usuarios");
        return userService.getAllUser();
    }
}
