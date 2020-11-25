package cl.gestionusuarios.util.converter;

import cl.gestionusuarios.domain.Phone;
import cl.gestionusuarios.domain.User;
import cl.gestionusuarios.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class UserToUserDtoConverter {

    public UserDto convert(User source, List<Phone> phones) {
        return UserDto.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .phones(phones.stream()
                        .map(new PhoneToPhoneDtoConverter()::convert)
                        .collect(Collectors.toList()))
                .created(source.getCreated())
                .modified(source.getModified())
                .lastLogin(source.getLastLogin())
                .token(source.getToken())
                .isActive(source.getIsActive())
                .build();
    }

    public List<UserDto> convert(List<User> source, List<Phone> phones) {
        List<UserDto> listUser = new ArrayList<>();

        for (User u : source) {
            listUser.add(UserDto.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .email(u.getEmail())
                    .phones(phones.stream()
                            .filter(f -> f.getUser().getId().equals(u.getId()))
                            .map(new PhoneToPhoneDtoConverter()::convert)
                            .collect(Collectors.toList()))
                    .created(u.getCreated())
                    .modified(u.getModified())
                    .lastLogin(u.getLastLogin())
                    .token(u.getToken())
                    .isActive(u.getIsActive())
                    .build());
        }
        return listUser;
    }
}
