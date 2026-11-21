package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
import fr.ece.rentauto.controllers.dtos.VehicleRequestDTO;
import fr.ece.rentauto.controllers.dtos.VehicleResponseDTO;
import fr.ece.rentauto.domaines.entities.VehicleEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VehicleMapper {
    public static VehicleEntity mapToVehicleToAdd(VehicleRequestDTO request) {
        VehicleEntity Vehicle = new VehicleEntity();
        Vehicle.setBrand(request.getBrand());
        Vehicle.setRegisterNumber(request.getRegisterNumber());
        Vehicle.setYear(request.getYear());
        Vehicle.setDailyRate(request.getDailyRate());
        Vehicle.setModel(request.getModel());
        Vehicle.setStatus(VehicleStatus.AVAILABLE);
        Vehicle.setCategory(request.getCategory());
        Vehicle.setCreatedAt(LocalDateTime.now());
        return Vehicle;
    }

    public static VehicleEntity mapToVehicleToUpdate(VehicleEntity VehicleToUpdate, VehicleRequestDTO request) {
        VehicleToUpdate.setBrand(request.getBrand());
        VehicleToUpdate.setRegisterNumber(request.getRegisterNumber());
        VehicleToUpdate.setCategory(request.getCategory());
        VehicleToUpdate.setDailyRate(request.getDailyRate());
        VehicleToUpdate.setModel(request.getModel());
        VehicleToUpdate.setStatus(VehicleStatus.AVAILABLE);
        VehicleToUpdate.setYear(request.getYear());
        VehicleToUpdate.setUpdatedAt(LocalDateTime.now());
        return VehicleToUpdate;
    }

    public static VehicleResponseDTO mapToVehicleResponse(VehicleEntity Vehicle) {
        VehicleResponseDTO response = new VehicleResponseDTO();
        response.setId(Vehicle.getId());
        response.setBrand(Vehicle.getBrand());
        response.setRegisterNumber(Vehicle.getRegisterNumber());
        response.setCategory(Vehicle.getCategory());
        response.setDailyRate(Vehicle.getDailyRate());
        response.setModel(Vehicle.getModel());
        response.setStatus(Vehicle.getStatus());
        response.setYear(Vehicle.getYear());
        response.setAgency(AgencyMapper.mapToAgencySummaryResponse(Vehicle.getAgency()));
//        response.setEquipments(EquipmentMapper.mapToEquipmentToSummaryResponseList(Vehicle.getEquipments()));
        response.setMaintenances(MaintenanceMapper.mapToMaintenanceSummaryResponseList(Vehicle.getMaintenances()));
        response.setBookings(BookingMapper.mapToBookingSummaryResponseList(Vehicle.getBookings()));
        return response;
    }

    public static VehicleResponseDTO mapToVehicleSummaryResponse(VehicleEntity Vehicle) {
        VehicleResponseDTO response = new VehicleResponseDTO();
        response.setId(Vehicle.getId());
        response.setBrand(Vehicle.getBrand());
        response.setRegisterNumber(Vehicle.getRegisterNumber());
        response.setCategory(Vehicle.getCategory());
        response.setDailyRate(Vehicle.getDailyRate());
        response.setModel(Vehicle.getModel());
        response.setStatus(Vehicle.getStatus());
        response.setYear(Vehicle.getYear());
        return response;
    }

    public static List<VehicleResponseDTO> mapToVehicleResponseList(List<VehicleEntity> VehicleEntities) {
        if (VehicleEntities == null || VehicleEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return VehicleEntities.stream().map(VehicleMapper::mapToVehicleResponse).collect(Collectors.toList());
    }

    public static List<VehicleResponseDTO> mapToVehicleSummaryResponseList(List<VehicleEntity> VehicleEntities) {
        if (VehicleEntities == null || VehicleEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return VehicleEntities.stream().map(VehicleMapper::mapToVehicleSummaryResponse).collect(Collectors.toList());
    }

    public static List<VehicleResponseDTO> mapToVehicleSummaryResponseList(Set<VehicleEntity> VehicleEntities) {
        if (VehicleEntities == null || VehicleEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return VehicleEntities.stream().map(VehicleMapper::mapToVehicleSummaryResponse).collect(Collectors.toList());
    }
}
