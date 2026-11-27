package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.VehicleEntity;
import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    List<VehicleEntity> findAllByStatus(VehicleStatus status);
}
