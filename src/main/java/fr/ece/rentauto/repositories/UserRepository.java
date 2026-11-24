package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLoginAndPassword(String login, String password);

    boolean existsByLogin(String username);
}
