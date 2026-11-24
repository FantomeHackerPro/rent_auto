package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByContractId(Long contractId);
}
