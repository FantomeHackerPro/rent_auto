package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
