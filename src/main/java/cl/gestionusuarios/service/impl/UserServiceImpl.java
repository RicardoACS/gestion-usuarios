package cl.gestionusuarios.service.impl;

import cl.gestionusuarios.domain.Phone;
import cl.gestionusuarios.domain.User;
import cl.gestionusuarios.dto.CreateUserDto;
import cl.gestionusuarios.dto.UserDto;
import cl.gestionusuarios.repository.PhoneRepository;
import cl.gestionusuarios.repository.UserRepository;
import cl.gestionusuarios.service.UserService;
import cl.gestionusuarios.util.JwtUtil;
import cl.gestionusuarios.util.converter.CreateUserDtoToUserConverter;
import cl.gestionusuarios.util.converter.PhoneDtoToPhoneConverter;
import cl.gestionusuarios.util.converter.UserToUserDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cl.gestionusuarios.util.ErrorMessageUtil.errorMessage;

/**
 * Implementación de los servicios del usuario
 * <p>
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<UserDto> createUser(CreateUserDto request) {
        log.info("[Service] Se creara el usuario con email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            log.error("[Service] El correo ingresado {}, ya esta registrado", request.getEmail());
            return new ResponseEntity(errorMessage("El correo ya esta registrado"), HttpStatus.CONFLICT);
        }

        try {
            log.info("[Service] Se mandara a crear el usuario a la DB");
            User user = userRepository.save(new CreateUserDtoToUserConverter(jwtUtil).convert(request));

            log.info("[Service] Usuario agregado con exito, ahora vamos a creaar los telefonos del usuario");
            phoneRepository.saveAll(new PhoneDtoToPhoneConverter().convert(request.getPhones(), user));

            log.info("[Service] Retornara el usuario");
            return new ResponseEntity(new UserToUserDtoConverter(phoneRepository).convert(user),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al crear el usuario: ", e);
            return new ResponseEntity(errorMessage("Ha ocurrido un error inesperado al crear el usuario"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String id, CreateUserDto request) {
        log.info("[Service] Se modificara un usuario con id: {}", id);

        Optional<User> user = userRepository.findUserById(id);

        if (!user.isPresent()) {
            log.error("El usuario {} solicitado, no se encuntra en la db", id);
            return new ResponseEntity(errorMessage("Usuario no se encuentra registrado"), HttpStatus.NOT_FOUND);
        }

        try {
            log.info("[Service] Se mandara a crear el usuario a la DB");
            User userUpdate = userRepository.save(new CreateUserDtoToUserConverter(jwtUtil)
                    .convertExist(request, user.get()));

            log.info("[Service] Usuario agregado con exito, ahora vamos a creaar los telefonos del usuario");
            List<Phone> phones = phoneRepository.getAllPhoneByUserId(id);
            phoneRepository.deleteAll(phones);

            phoneRepository.saveAll(new PhoneDtoToPhoneConverter()
                    .convert(request.getPhones(), userUpdate));

            log.info("[Service] Retornara el usuario");

            return new ResponseEntity(new UserToUserDtoConverter(phoneRepository).convert(userUpdate),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al modificar el usuario: ", e);
            return new ResponseEntity(errorMessage("Ha ocurrido un error inesperado al modificar el usuario"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUser() {
        log.info("[Service] Se obtendran todos los usuarios activos");
        try {
            List<User> userList = userRepository.findAll();

            return new ResponseEntity(userList.stream()
                    .map(new UserToUserDtoConverter(phoneRepository)::convert)
                    .filter(f -> f.getIsActive())
                    .collect(Collectors.toList()),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al buscar todos los usuarios: ", e);
            return new ResponseEntity(errorMessage("Ha ocurrido un error inesperado al buscar todos los usuarios"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        log.info("[Service] Se obtendran el usuario con id: {}", id);
        try {
            Optional<User> user = userRepository.findUserById(id);

            if (!user.isPresent()) {
                log.error("El usuario {} solicitado, no se encuntra en la db", id);
                return new ResponseEntity(errorMessage("Usuario no se encuentra registrado"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(new UserToUserDtoConverter(phoneRepository).convert(user.get()),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al buscar el usuario: ", e);
            return new ResponseEntity(errorMessage("Ha ocurrido un error inesperado al buscar el usuario"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> changeState(String id, Boolean state) {
        log.info("[Service] Se inactivara el usuario con id: {}", id);
        try {
            Optional<User> user = userRepository.findUserById(id);

            if (!user.isPresent()) {
                log.error("El usuario {} solicitado, no se encuntra en la db", id);
                return new ResponseEntity(errorMessage("Usuario no se encuentra registrado"), HttpStatus.NOT_FOUND);
            }

            user.get().setIsActive(state);
            user.get().setModified(new Date());
            userRepository.save(user.get());
            return new ResponseEntity(errorMessage("Usuario actualizado con exito"), HttpStatus.OK);

        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al desactivar el usuario: ", e);
            return new ResponseEntity(errorMessage("Ha ocurrido un error inesperado al desactivar el usuario"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
