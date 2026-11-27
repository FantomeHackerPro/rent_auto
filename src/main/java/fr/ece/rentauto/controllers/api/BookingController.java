package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.*;
import fr.ece.rentauto.domaines.entities.BookingEntity;
import fr.ece.rentauto.domaines.mappers.BookingMapper;
import fr.ece.rentauto.domaines.mappers.ContractMapper;
import fr.ece.rentauto.services.BookingService;
import fr.ece.rentauto.services.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;
    private ContractService contractService;

    @GetMapping
    public ResponseMessage<List<BookingResponseDTO>> getBookings() {
        List<BookingResponseDTO> bookings = BookingMapper.mapToBookingResponseList(bookingService.getBookings());
        int code = HttpStatus.NOT_FOUND.value();
        if (!bookings.isEmpty()) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<List<BookingResponseDTO>> response = new ResponseMessage<>();
        response.setMessage("List of bookings");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(bookings);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseMessage<BookingResponseDTO> getBooking(@PathVariable Long id) throws AppException {
        BookingResponseDTO booking = BookingMapper.mapToBookingResponse(bookingService.getBooking(id));
        int code = HttpStatus.NOT_FOUND.value();
        if (booking != null) {
            code = HttpStatus.OK.value();
        }
        ResponseMessage<BookingResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Booking");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(booking);
        return response;
    }

    @PostMapping("/create")
    public ResponseMessage<?> addBooking(@RequestBody BookingRequestDTO request) throws AppException {
        BookingEntity booking = this.bookingService.addBooking(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<ContractResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Booking has been created successfully");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(ContractMapper.mapToContractResponse(booking.getContract()));
        return response;
    }

    @PutMapping("{id}/update")
    public ResponseMessage<?> updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO request) throws AppException {
        this.bookingService.updateBooking(id, request);
        int code = HttpStatus.OK.value();
        ResponseMessage<BookingResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Booking has been updated successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @PostMapping("/estimation")
    public ResponseMessage<BigDecimal> amountEstimation(@RequestBody AmountEstimationRequestDTO request) {
        BigDecimal amount = bookingService.amountEstimation(request);
        int code = HttpStatus.OK.value();
        ResponseMessage<BigDecimal> response = new ResponseMessage<>();
        response.setMessage("Amount estimation");
        response.setStatus(code);
        response.setCode(code);
        response.getData().add(amount);
        return response;
    }

}
