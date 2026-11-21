package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {
}
