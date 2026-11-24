package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.PaymentEntity;
import fr.ece.rentauto.controllers.dtos.PaymentRequestDTO;
import fr.ece.rentauto.domaines.mappers.PaymentMapper;
import fr.ece.rentauto.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentProvider implements PaymentService{

    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentEntity> getPayments() {
        return this.paymentRepository.findAll();
    }

    @Override
    public List<PaymentEntity> getPayments(Long contractId) {
        return this.paymentRepository.findByContractId(contractId);
    }

    @Override
    public PaymentEntity getPayment(Long id) throws AppException {
        return this.paymentRepository.findById(id).orElseThrow(() -> new AppException("Payment not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addPayment(PaymentRequestDTO request) {
        this.paymentRepository.save(PaymentMapper.mapToPaymentToAdd(request));
    }

    @Transactional
    @Override
    public void updatePayment(Long id, PaymentRequestDTO request) throws AppException {
        this.paymentRepository.save(PaymentMapper.mapToPaymentToUpdate(this.getPayment(id), request));
    }

    @Transactional
    @Override
    public void deletePayment(Long id) {

    }
}
