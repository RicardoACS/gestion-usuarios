package cl.gestionusuarios.service;

import cl.gestionusuarios.dto.CreateUserDto;
import cl.gestionusuarios.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interfaz para los servicios de usuario
 * <p>
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public interface UserService {

    /**
     * Método para crear un usuario en la DB
     *
     * @param request datos del usuario
     * @return Usuario creado
     */
    ResponseEntity<UserDto> createUser(CreateUserDto request);

    /**
     * Método para modificar un usuario en la DB
     *
     * @param request datos del usuario
     * @param id      identificador del usuario
     * @return Usuario modificado
     */
    ResponseEntity<UserDto> updateUser(String id, CreateUserDto request);

    /**
     * Método para mostrar todos los usuarios activos
     *
     * @return Usuarios
     */
    ResponseEntity<List<UserDto>> getAllUser();

    /**
     * Método para mostrar un usuario
     *
     * @param id Identificador del usuario
     * @return Usuario por id
     */
    ResponseEntity<UserDto> getUserById(String id);

    /**
     * Método para desactivar un usuario
     *
     * @param id    Identificador del usuario
     * @param state estado del usuario
     * @return mensaje
     */
    ResponseEntity<String> changeState(String id, Boolean state);

}
