package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.PaymentEntity;
import fr.ece.rentauto.controllers.dtos.PaymentRequestDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentEntity> getPayments();
    List<PaymentEntity> getPayments(Long contractId);
    PaymentEntity getPayment(Long id) throws AppException;
    void  addPayment(PaymentRequestDTO request);
    void updatePayment(Long id, PaymentRequestDTO request) throws AppException;
    void deletePayment(Long id);
}
