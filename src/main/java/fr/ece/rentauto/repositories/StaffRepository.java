package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
}
