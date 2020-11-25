package cl.gestionusuarios.repository;

import cl.gestionusuarios.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de los Teléfonos
 * <p>
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    /**
     * Busca todos los teléfonos que tenga el usuario
     *
     * @param id identificador del usuario
     * @return Listado de teléfonos
     */
    List<Phone> getAllPhoneByUserId(String id);

}
