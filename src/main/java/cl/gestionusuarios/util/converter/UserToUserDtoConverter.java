package cl.gestionusuarios.util.converter;

import cl.gestionusuarios.domain.Phone;
import cl.gestionusuarios.domain.User;
import cl.gestionusuarios.dto.UserDto;
import cl.gestionusuarios.repository.PhoneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class UserToUserDtoConverter {

    private PhoneRepository phoneRepository;

    public UserToUserDtoConverter(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public UserDto convert(User source) {
        return UserDto.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .phones(phoneRepository.getAllPhoneByUserId(source.getId())
                        .stream()
                        .map(new PhoneToPhoneDtoConverter()::convert)
                        .collect(Collectors.toList()))
                .created(source.getCreated())
                .modified(source.getModified())
                .lastLogin(source.getLastLogin())
                .token(source.getToken())
                .isActive(source.getIsActive())
                .build();
    }
}
