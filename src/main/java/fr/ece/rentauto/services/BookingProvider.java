package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.BookingEntity;
import fr.ece.rentauto.controllers.dtos.BookingRequestDTO;
import fr.ece.rentauto.domaines.mappers.BookingMapper;
import fr.ece.rentauto.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookingProvider implements BookingService {

    private BookingRepository bookingRepository;

    @Override
    public List<BookingEntity> getBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public BookingEntity getBooking(Long id) throws AppException {
        return this.bookingRepository.findById(id).orElseThrow(() -> new AppException("Booking not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public void addBooking(BookingRequestDTO request) {
        this.bookingRepository.save(BookingMapper.mapToBookingToAdd(request));
    }

    @Override
    public void updateBooking(Long id, BookingRequestDTO request) throws AppException {
        this.bookingRepository.save(BookingMapper.mapToBookingToUpdate(this.getBooking(id), request));
    }

    @Override
    public void deleteBooking(BookingEntity booking) {

    }
}
