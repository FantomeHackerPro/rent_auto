package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.PaymentEntity;
import fr.ece.rentauto.controllers.dtos.PaymentRequestDTO;
import fr.ece.rentauto.controllers.dtos.PaymentResponseDTO;
import fr.ece.rentauto.domaines.enumerations.PaymentMode;
import fr.ece.rentauto.domaines.enumerations.PaymentStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {
    public static PaymentEntity mapToPaymentToAdd(PaymentRequestDTO request) {
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getPaymentMode().equals(PaymentMode.CASH) ? PaymentStatus.CONFIRMED : PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        return payment;
    }

    public static PaymentEntity mapToPaymentToUpdate(PaymentEntity paymentToUpdate, PaymentRequestDTO request) {
        paymentToUpdate.setAmount(request.getAmount());
        paymentToUpdate.setPaymentDate(request.getPaymentDate());
        paymentToUpdate.setPaymentMode(request.getPaymentMode());
        paymentToUpdate.setStatus(request.getStatus());
        paymentToUpdate.setUpdatedAt(LocalDateTime.now());
        return paymentToUpdate;
    }

    public static PaymentResponseDTO mapToPaymentResponse(PaymentEntity payment) {
        if (payment == null) {
            return null;
        }
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentMode(payment.getPaymentMode());
        response.setStatus(payment.getStatus());
        response.setContract(ContractMapper.mapToContractSummaryResponse(payment.getContract()));
        return  response;
    }

    public static PaymentResponseDTO mapToPaymentSummaryResponse(PaymentEntity payment) {
        if (payment == null) {
            return null;
        }
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentMode(payment.getPaymentMode());
        response.setStatus(payment.getStatus());
        return  response;
    }

    public static List<PaymentResponseDTO> mapToPaymentResponseList(List<PaymentEntity> payments) {
        if (payments.isEmpty()) {
            return new ArrayList<>();
        }
        return payments.stream().map(PaymentMapper::mapToPaymentResponse).collect(Collectors.toList());
    }

    public static List<PaymentResponseDTO> mapToPaymentSummaryResponseList(List<PaymentEntity> payments) {
        if (payments.isEmpty()) {
            return new ArrayList<>();
        }
        return payments.stream().map(PaymentMapper::mapToPaymentSummaryResponse).collect(Collectors.toList());
    }
}
