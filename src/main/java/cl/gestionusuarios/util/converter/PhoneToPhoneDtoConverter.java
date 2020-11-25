package cl.gestionusuarios.util.converter;

import cl.gestionusuarios.domain.Phone;
import cl.gestionusuarios.dto.PhoneDto;
import org.springframework.core.convert.converter.Converter;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class PhoneToPhoneDtoConverter implements Converter<Phone, PhoneDto> {

    @Override
    public PhoneDto convert(Phone source) {
        return PhoneDto.builder()
                .citycode(source.getCitycode())
                .countrycode(source.getCountrycode())
                .number(source.getNumber())
                .build();
    }
}
