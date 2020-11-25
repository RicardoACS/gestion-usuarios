package cl.gestionusuarios.util.converter;

import cl.gestionusuarios.domain.User;
import cl.gestionusuarios.dto.CreateUserDto;
import cl.gestionusuarios.util.JwtUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.util.UUID;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class CreateUserDtoToUserConverter implements Converter<CreateUserDto, User> {

    private final JwtUtil jwtUtil;

    public CreateUserDtoToUserConverter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User convert(CreateUserDto source) {
        Date now = new Date();
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(source.getName())
                .email(source.getEmail())
                .password(source.getPassword())
                .created(now)
                .isActive(true)
                .lastLogin(now)
                .token(jwtUtil.getJWTToken(source.getEmail()))
                .build();
    }

    public User convertExist(CreateUserDto source, User userExist) {
        Date now = new Date();
        return User.builder()
                .id(userExist.getId())
                .name(source.getName())
                .email(source.getEmail())
                .password(source.getPassword())
                .created(userExist.getCreated())
                .modified(now)
                .isActive(true)
                .lastLogin(now)
                .token(jwtUtil.getJWTToken(source.getEmail()))
                .build();
    }

}
