package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.AmountEstimationRequestDTO;
import fr.ece.rentauto.controllers.dtos.BookingPayloadRequestDTO;
import fr.ece.rentauto.controllers.dtos.BookingRequestDTO;
import fr.ece.rentauto.controllers.dtos.BookingResponseDTO;
import fr.ece.rentauto.domaines.entities.BookingEntity;

import java.math.BigDecimal;
import java.util.List;

public interface BookingService {
    List<BookingEntity> getBookings();
    BookingEntity getBooking(Long id) throws AppException;
    BookingEntity addBooking(BookingRequestDTO request) throws AppException;
    void updateBooking(Long id, BookingRequestDTO request) throws AppException;
    void deleteBooking(BookingEntity booking);
    BigDecimal amountEstimation(AmountEstimationRequestDTO request);
}
