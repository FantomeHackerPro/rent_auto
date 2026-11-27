package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.AmountEstimationRequestDTO;
import fr.ece.rentauto.controllers.dtos.ContractRequestDTO;
import fr.ece.rentauto.domaines.entities.BookingEntity;
import fr.ece.rentauto.controllers.dtos.BookingRequestDTO;
import fr.ece.rentauto.domaines.entities.ContractEntity;
import fr.ece.rentauto.domaines.entities.VehicleEntity;
import fr.ece.rentauto.domaines.mappers.BookingMapper;
import fr.ece.rentauto.domaines.mappers.ContractMapper;
import fr.ece.rentauto.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@Service
public class BookingProvider implements BookingService {

    private BookingRepository bookingRepository;
    private CustomerService customerService;
    private VehicleService vehicleService;

    @Override
    public List<BookingEntity> getBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public BookingEntity getBooking(Long id) throws AppException {
        return this.bookingRepository.findById(id).orElseThrow(() -> new AppException("Booking not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public BookingEntity addBooking(BookingRequestDTO request) throws AppException {

        BookingEntity bookingToAdd = BookingMapper.mapToBookingToAdd(request);
        VehicleEntity vehicle = vehicleService.getVehicle(request.getVehicleId());
        bookingToAdd.setCustomer(customerService.getCustomer(request.getCustomerId()));
        bookingToAdd.setVehicle(vehicle);

        ContractRequestDTO contractRequest = new ContractRequestDTO();
        AmountEstimationRequestDTO amountEstimationRequest = new AmountEstimationRequestDTO();
        amountEstimationRequest.setAmount(vehicle.getDailyRate());
        amountEstimationRequest.setEndDate(request.getEndDate());
        amountEstimationRequest.setStartDate(request.getStartDate());
        contractRequest.setPrice(this.amountEstimation(amountEstimationRequest));
        ContractEntity contractToAdd = ContractMapper.mapToContractToAdd(contractRequest);

        bookingToAdd.setContract(contractToAdd);

        return this.bookingRepository.save(bookingToAdd);
    }

    @Transactional
    @Override
    public void updateBooking(Long id, BookingRequestDTO request) throws AppException {
        BookingEntity bookingToUpdate = BookingMapper.mapToBookingToUpdate(this.getBooking(id), request);
        VehicleEntity vehicle = vehicleService.getVehicle(request.getVehicleId());
//        bookingToUpdate.setCustomer(customerService.getCustomer(request.getCustomerId()));
        bookingToUpdate.setVehicle(vehicle);

        AmountEstimationRequestDTO amountEstimationRequest = new AmountEstimationRequestDTO();
        amountEstimationRequest.setAmount(vehicle.getDailyRate());
        amountEstimationRequest.setEndDate(request.getEndDate());
        amountEstimationRequest.setStartDate(request.getStartDate());

        ContractEntity contractToUpdate = bookingToUpdate.getContract();
        contractToUpdate.setPrice(this.amountEstimation(amountEstimationRequest));

        bookingToUpdate.setContract(contractToUpdate);

        this.bookingRepository.save(bookingToUpdate);
    }

    @Transactional
    @Override
    public void deleteBooking(BookingEntity booking) {

    }

    @Override

    public BigDecimal amountEstimation(AmountEstimationRequestDTO request) {
        int MONETARY_SCALE = 2;
        RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        // +1 to include both startDate and endDate (e.g., 22/11 to 24/11 = 3 days)
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        return request.getAmount()
                .multiply(BigDecimal.valueOf(numberOfDays))
                .setScale(MONETARY_SCALE, ROUNDING_MODE);
    }
}
