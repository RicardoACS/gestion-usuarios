package cl.gestionusuarios.controller

import cl.gestionusuarios.dto.CreateUserDto
import cl.gestionusuarios.dto.PhoneDto
import cl.gestionusuarios.dto.UserDto
import cl.gestionusuarios.service.UserService
import cl.gestionusuarios.util.validator.UserValidator
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.mockito.ArgumentMatchers.anyBoolean
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.when

/**

 Ricardo Carrasco S
 25-11-2020
 **/
class UserControllerTest extends Specification {
    @Mock
    UserService userService
    @Mock
    UserValidator userValidator
    @InjectMocks
    UserController userController

    def setup() {
        MockitoAnnotations.initMocks(this)
    }

    def "test create User"() {
        given:
        def createUser = new CreateUserDto("Usuario", "email@correo.cl", "Hola12",
                [new PhoneDto("56988745", "02", "59")])
        ResponseEntity<UserDto> user = new ResponseEntity(new UserDto(UUID.randomUUID().toString(),
                "name", "correo@corre.cl", "Hola12", new Date(), null, new Date(), UUID.randomUUID().toString(),
                true, [new PhoneDto("56988745", "02", "59")]), HttpStatus.OK);
        when(userService.createUser(createUser)).thenReturn(user)

        when:
        ResponseEntity<UserDto> result =
                userController.createUser(createUser)

        then:
        result.getBody() == user.getBody()
    }

    def "test update User"() {
        given:
        def idUser = UUID.randomUUID().toString()
        def createUser = new CreateUserDto("Usuario", "email@correo.cl", "Hola12",
                [new PhoneDto("56988746", "05", "60")])
        ResponseEntity<UserDto> user = new ResponseEntity(new UserDto(idUser,
                "name", "correo@corre.cl", "Hola12", new Date(), new Date(), new Date(),
                UUID.randomUUID().toString(), true, [new PhoneDto("56988745", "02",
                "59")]), HttpStatus.OK);
        when(userService.updateUser(idUser, createUser)).thenReturn(user)

        when:
        ResponseEntity<UserDto> result = userController
                .updateUser(idUser, createUser)

        then:
        result.getBody() == user.getBody()
    }

    def "test change State"() {
        def idUser = UUID.randomUUID().toString()
        given:
        def createUser = new CreateUserDto("Usuario", "email@correo.cl", "Hola12",
                [new PhoneDto("56988745", "02", "59")])
        ResponseEntity<UserDto> user = new ResponseEntity(new UserDto(idUser,
                "name", "correo@corre.cl", "Hola12", new Date(), null, new Date(), UUID.randomUUID().toString(),
                true, [new PhoneDto("56988745", "02", "59")]), HttpStatus.OK);
        when(userService.createUser(createUser)).thenReturn(user)
        ResponseEntity<String> response = new ResponseEntity("{mensaje: 'Usuario actualizado con éxito'}", HttpStatus.OK);
        when(userService.changeState(idUser, Boolean.TRUE)).thenReturn(response)

        when:
        ResponseEntity<String> result = userController.changeState(idUser, Boolean.TRUE)

        then:
        result == response
    }

    def "test get User By Id"() {
        def idUser = UUID.randomUUID().toString()
        given:
        def createUser = new CreateUserDto("Usuario", "email@correo.cl", "Hola12",
                [new PhoneDto("56988745", "02", "59")])
        ResponseEntity<UserDto> user = new ResponseEntity(new UserDto(idUser,
                "name", "correo@corre.cl", "Hola12", new Date(), null, new Date(), UUID.randomUUID().toString(),
                true, [new PhoneDto("56988745", "02", "59")]), HttpStatus.OK);
        when(userService.getUserById(idUser)).thenReturn(user)

        when:
        ResponseEntity<UserDto> result = userController.getUserById(idUser)

        then:
        result.getBody() == user.getBody()
    }

    def "test get All User"() {
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(UUID.randomUUID().toString(),
                "name", "correo@corre.cl", "Hola12", new Date(), null,
                new Date(), UUID.randomUUID().toString(), true,
                [new PhoneDto("56988745", "02", "59")]))
        users.add(new UserDto(UUID.randomUUID().toString(),
                "name", "correo@corre.cl", "Hola12", new Date(), null,
                new Date(), UUID.randomUUID().toString(), true,
                [new PhoneDto("56988745", "02", "59")]))
        given:
        when(userService.getAllUser()).thenReturn(new ResponseEntity<List<UserDto>>(users, HttpStatus.OK))

        when:
        ResponseEntity<List<UserDto>> result = userController.getAllUser()

        then:
        result.getBody() == users
    }
}
