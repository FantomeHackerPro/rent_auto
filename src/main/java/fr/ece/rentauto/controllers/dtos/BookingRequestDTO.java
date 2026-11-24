package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequestDTO {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private BookingStatus status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long vehicleId;

}
