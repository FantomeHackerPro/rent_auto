package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
}
