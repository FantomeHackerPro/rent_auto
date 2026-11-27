package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.UserEntity;
import fr.ece.rentauto.domaines.enumerations.UserRole;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    boolean existsByLogin(String username);

    boolean existsByRole(UserRole userRole);
}
