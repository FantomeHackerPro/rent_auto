package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.AgencyEntity;
import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {
}
