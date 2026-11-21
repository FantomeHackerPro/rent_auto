package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.configs.ResponseMessage;
import fr.ece.rentauto.controllers.dtos.BookingRequestDTO;
import fr.ece.rentauto.controllers.dtos.BookingResponseDTO;
import fr.ece.rentauto.domaines.mappers.BookingMapper;
import fr.ece.rentauto.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

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
        this.bookingService.addBooking(request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<BookingResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Booking has been created successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

    @PutMapping("{id}/update")
    public ResponseMessage<?> updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO request) throws AppException {
        this.bookingService.updateBooking(id, request);
        int code = HttpStatus.CREATED.value();
        ResponseMessage<BookingResponseDTO> response = new ResponseMessage<>();
        response.setMessage("Booking has been updated successfully");
        response.setStatus(code);
        response.setCode(code);
        return response;
    }

}
