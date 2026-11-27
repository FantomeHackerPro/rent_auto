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
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand(request.getBrand());
        vehicle.setRegisterNumber(request.getRegisterNumber());
        vehicle.setYear(request.getYear());
        vehicle.setDailyRate(request.getDailyRate());
        vehicle.setModel(request.getModel());
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicle.setCategory(request.getCategory());
        vehicle.setCreatedAt(LocalDateTime.now());
        return vehicle;
    }

    public static VehicleEntity mapToVehicleToUpdate(VehicleEntity vehicleToUpdate, VehicleRequestDTO request) {
        vehicleToUpdate.setBrand(request.getBrand());
        vehicleToUpdate.setRegisterNumber(request.getRegisterNumber());
        vehicleToUpdate.setCategory(request.getCategory());
        vehicleToUpdate.setDailyRate(request.getDailyRate());
        vehicleToUpdate.setModel(request.getModel());
        vehicleToUpdate.setStatus(VehicleStatus.AVAILABLE);
        vehicleToUpdate.setYear(request.getYear());
        vehicleToUpdate.setUpdatedAt(LocalDateTime.now());
        return vehicleToUpdate;
    }

    public static VehicleResponseDTO mapToVehicleResponse(VehicleEntity vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleResponseDTO response = new VehicleResponseDTO();
        response.setId(vehicle.getId());
        response.setBrand(vehicle.getBrand());
        response.setRegisterNumber(vehicle.getRegisterNumber());
        response.setCategory(vehicle.getCategory());
        response.setDailyRate(vehicle.getDailyRate());
        response.setModel(vehicle.getModel());
        response.setStatus(vehicle.getStatus());
        response.setYear(vehicle.getYear());
        response.setImageUrl(vehicle.getImageUrl());
        response.setAgency(AgencyMapper.mapToAgencySummaryResponse(vehicle.getAgency()));
        response.setEquipments(EquipmentMapper.mapToEquipmentToSummaryResponseList(vehicle.getEquipments()));
        response.setMaintenances(MaintenanceMapper.mapToMaintenanceSummaryResponseList(vehicle.getMaintenances()));
        response.setBookings(BookingMapper.mapToBookingSummaryResponseList(vehicle.getBookings()));
        return response;
    }

    public static VehicleResponseDTO mapToVehicleSummaryResponse(VehicleEntity vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleResponseDTO response = new VehicleResponseDTO();
        response.setId(vehicle.getId());
        response.setBrand(vehicle.getBrand());
        response.setRegisterNumber(vehicle.getRegisterNumber());
        response.setCategory(vehicle.getCategory());
        response.setDailyRate(vehicle.getDailyRate());
        response.setModel(vehicle.getModel());
        response.setStatus(vehicle.getStatus());
        response.setYear(vehicle.getYear());
        response.setImageUrl(vehicle.getImageUrl());
        return response;
    }

    public static List<VehicleResponseDTO> mapToVehicleResponseList(List<VehicleEntity> vehicleEntities) {
        if (vehicleEntities == null || vehicleEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return vehicleEntities.stream().map(VehicleMapper::mapToVehicleResponse).collect(Collectors.toList());
    }

    public static List<VehicleResponseDTO> mapToVehicleSummaryResponseList(List<VehicleEntity> vehicleEntities) {
        if (vehicleEntities == null || vehicleEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return vehicleEntities.stream().map(VehicleMapper::mapToVehicleSummaryResponse).collect(Collectors.toList());
    }

    public static List<VehicleResponseDTO> mapToVehicleSummaryResponseList(Set<VehicleEntity> vehicleEntities) {
        if (vehicleEntities == null || vehicleEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return vehicleEntities.stream().map(VehicleMapper::mapToVehicleSummaryResponse).collect(Collectors.toList());
    }
}