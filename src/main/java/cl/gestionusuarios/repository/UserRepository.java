package cl.gestionusuarios.repository;

import cl.gestionusuarios.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de los usuarios
 * <p>
 * Ricardo Carrasco S
 * 24-11-2020
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca si el correo existe en la db
     *
     * @param email Email del usuario
     * @return Si existe correo o no
     */
    Boolean existsByEmail(String email);

    /**
     * Busca un usuario por Id
     *
     * @param id Identificador del usuario
     * @return datos del usuario
     */
    Optional<User> findUserById(String id);
}
