package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
}
