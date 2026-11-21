package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
