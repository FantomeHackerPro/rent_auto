package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.VehicleCategory;
import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleResponseDTO {

    private Long id;

    private String registerNumber;

    private String brand;

    private String model;

    private VehicleCategory category;

    private BigDecimal dailyRate;

    private int year;

    private VehicleStatus status;

    private AgencyResponseDTO  agency;

    private List<EquipmentResponseDTO> equipments;

    private List<MaintenanceResponseDTO> maintenances;

    private List<BookingResponseDTO> bookings;

    private String imageUrl;

}
