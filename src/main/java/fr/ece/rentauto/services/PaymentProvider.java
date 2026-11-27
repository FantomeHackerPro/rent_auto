package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.BookingEntity;
import fr.ece.rentauto.domaines.entities.ContractEntity;
import fr.ece.rentauto.domaines.entities.PaymentEntity;
import fr.ece.rentauto.controllers.dtos.PaymentRequestDTO;
import fr.ece.rentauto.domaines.entities.VehicleEntity;
import fr.ece.rentauto.domaines.enumerations.BookingStatus;
import fr.ece.rentauto.domaines.enumerations.PaymentMode;
import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
import fr.ece.rentauto.domaines.mappers.PaymentMapper;
import fr.ece.rentauto.repositories.BookingRepository;
import fr.ece.rentauto.repositories.PaymentRepository;
import fr.ece.rentauto.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentProvider implements PaymentService{

    private PaymentRepository paymentRepository;
    private ContractService contractService;
    private BookingRepository  bookingRepository;
    private VehicleRepository vehicleRepository;

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
    public void addPayment(PaymentRequestDTO request) throws AppException {
        PaymentEntity paymentToAdd = PaymentMapper.mapToPaymentToAdd(request);
        ContractEntity contract = contractService.getContract(request.getContractId());
        paymentToAdd.setContract(contract);
        if (request.getPaymentMode().equals(PaymentMode.CASH)) {
            BookingEntity booking = contract.getBooking();
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);

            VehicleEntity vehicle = booking.getVehicle();
            vehicle.setStatus(VehicleStatus.RENT);
            vehicleRepository.save(vehicle);
        }
        this.paymentRepository.save(paymentToAdd);
    }

    @Transactional
    @Override
    public void updatePayment(Long id, PaymentRequestDTO request) throws AppException {
        PaymentEntity paymentToUpdate = PaymentMapper.mapToPaymentToAdd(request);
        paymentToUpdate.setContract(contractService.getContract(request.getContractId()));
        this.paymentRepository.save(PaymentMapper.mapToPaymentToUpdate(this.getPayment(id), request));
    }

    @Transactional
    @Override
    public void deletePayment(Long id) {

    }
}
