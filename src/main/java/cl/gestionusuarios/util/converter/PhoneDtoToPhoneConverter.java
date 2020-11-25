package cl.gestionusuarios.util.converter;

import cl.gestionusuarios.domain.Phone;
import cl.gestionusuarios.domain.User;
import cl.gestionusuarios.dto.PhoneDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class PhoneDtoToPhoneConverter {

    public List<Phone> convert(List<PhoneDto> source, User user) {

        List<Phone> phones = new ArrayList<>();

        for (PhoneDto p : source) {
            phones.add(Phone.builder()
                    .number(p.getNumber())
                    .citycode(p.getCitycode())
                    .countrycode(p.getCountrycode())
                    .user(user)
                    .build());
        }

        return phones;
    }
}
