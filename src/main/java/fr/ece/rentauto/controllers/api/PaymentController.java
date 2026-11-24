package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.PaymentRequestDTO;
import fr.ece.rentauto.controllers.dtos.PaymentResponseDTO;
import fr.ece.rentauto.domaines.mappers.PaymentMapper;
import fr.ece.rentauto.services.PaymentService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping
    public ResponseMessage<List<PaymentResponseDTO>> getAgencies() {
        List<PaymentResponseDTO> payments = PaymentMapper.mapToPaymentResponseList(paymentService.getPayments());
        return getListResponseMessage(payments);
    }

    @GetMapping("/{id}")
    public ResponseMessage<PaymentResponseDTO> getPayment(@PathVariable Long id) throws AppException {
        PaymentResponseDTO Payment = PaymentMapper.mapToPaymentResponse(paymentService.getPayment(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (Payment != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<PaymentResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Payment");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(Payment);
        return response;
    }

    @GetMapping("/{contractId}/contract")
    public ResponseMessage<List<PaymentResponseDTO>> getPaymentByBookingId(@PathVariable Long contractId) throws AppException {
        List<PaymentResponseDTO> payments = PaymentMapper.mapToPaymentResponseList(paymentService.getPayments(contractId));
        return getListResponseMessage(payments);
    }

    @NonNull
    private ResponseMessage<List<PaymentResponseDTO>> getListResponseMessage(List<PaymentResponseDTO> payments) {
        int code = HttpStatus.NOT_FOUND.value();
        if (!payments.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<PaymentResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of payments");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(payments);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> addPayment(@RequestBody PaymentRequestDTO request) throws AppException {
        paymentService.addPayment(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<PaymentResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Payment has been created successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("{id}/update")
    public ResponseMessage<?> updatePayment(@PathVariable Long id, @RequestBody PaymentRequestDTO request) throws AppException {
        paymentService.updatePayment(id, request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<PaymentResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Payment has been updated successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }
    
}
