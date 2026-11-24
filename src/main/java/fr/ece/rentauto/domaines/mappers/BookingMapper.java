package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.controllers.dtos.BookingRequestDTO;
import fr.ece.rentauto.controllers.dtos.BookingResponseDTO;
import fr.ece.rentauto.domaines.entities.BookingEntity;
import fr.ece.rentauto.domaines.enumerations.BookingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {
    public static BookingEntity mapToBookingToAdd(BookingRequestDTO request) {
        BookingEntity booking = new BookingEntity();
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        return booking;
    }

    public static BookingEntity mapToBookingToUpdate(BookingEntity booking, BookingRequestDTO request) {
        booking.setEndDate(request.getEndDate());
        booking.setStartDate(request.getStartDate());
        booking.setStatus(request.getStatus());
        booking.setUpdatedAt(LocalDateTime.now());

        return booking;
    }

    public static BookingResponseDTO mapToBookingResponse(BookingEntity booking) {
        if (booking == null) {
            return null;
        }
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setStatus(booking.getStatus());
        response.setCustomer(CustomerMapper.mapToCustomerSummaryResponse(booking.getCustomer()));
        response.setContract(ContractMapper.mapToContractSummaryResponse(booking.getContract()));
        response.setVehicle(VehicleMapper.mapToVehicleSummaryResponse(booking.getVehicle()));
        return response;
    }

    public static BookingResponseDTO mapToBookingSummaryResponse(BookingEntity booking) {
        if (booking == null) {
            return null;
        }
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setStatus(booking.getStatus());
        return response;
    }

    public static List<BookingResponseDTO> mapToBookingResponseList(List<BookingEntity> bookingEntities) {
        if (bookingEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return bookingEntities.stream().map(BookingMapper::mapToBookingResponse).collect(Collectors.toList());
    }

    public static List<BookingResponseDTO> mapToBookingSummaryResponseList(List<BookingEntity> bookingEntities) {
        if (bookingEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return bookingEntities.stream().map(BookingMapper::mapToBookingSummaryResponse).collect(Collectors.toList());
    }
}
