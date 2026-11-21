package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
