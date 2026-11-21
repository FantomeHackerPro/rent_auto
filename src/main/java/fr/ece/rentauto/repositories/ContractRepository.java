package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
}
