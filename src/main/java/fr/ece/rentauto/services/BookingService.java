package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.BookingEntity;
import fr.ece.rentauto.controllers.dtos.BookingRequestDTO;

import java.util.List;

public interface BookingService {
    List<BookingEntity> getBookings();
    BookingEntity getBooking(Long id) throws AppException;
    void addBooking(BookingRequestDTO request);
    void updateBooking(Long id, BookingRequestDTO request) throws AppException;
    void deleteBooking(BookingEntity booking);
}
