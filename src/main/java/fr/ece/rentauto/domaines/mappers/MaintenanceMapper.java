package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.MaintenanceEntity;
import fr.ece.rentauto.controllers.dtos.MaintenanceRequestDTO;
import fr.ece.rentauto.controllers.dtos.MaintenanceResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaintenanceMapper {
    public static MaintenanceEntity mapToMaintenanceToAdd(MaintenanceRequestDTO request) {
        MaintenanceEntity maintenance = new MaintenanceEntity();
        maintenance.setDescription(request.getDescription());
        maintenance.setEndDate(request.getEndDate());
        maintenance.setStartDate(request.getStartDate());
        maintenance.setCreatedAt(LocalDateTime.now());
        return maintenance;
    }

    public static MaintenanceEntity mapToMaintenanceToUpdate(MaintenanceEntity maintenanceToUpdate, MaintenanceRequestDTO request) {
        maintenanceToUpdate.setDescription(request.getDescription());
        maintenanceToUpdate.setEndDate(request.getEndDate());
        maintenanceToUpdate.setStartDate(request.getStartDate());
        maintenanceToUpdate.setUpdatedAt(LocalDateTime.now());
        return maintenanceToUpdate;
    }

    public static MaintenanceResponseDTO mapToMaintenanceResponse(MaintenanceEntity maintenanceEntity) {
        MaintenanceResponseDTO response = new MaintenanceResponseDTO();
        response.setId(maintenanceEntity.getId());
        response.setDescription(maintenanceEntity.getDescription());
        response.setStartDate(maintenanceEntity.getStartDate());
        response.setEndDate(maintenanceEntity.getEndDate());
        response.setVehicle(VehicleMapper.mapToVehicleSummaryResponse(maintenanceEntity.getVehicle()));
        return response;
    }

    public static MaintenanceResponseDTO mapToMaintenanceSummaryResponse(MaintenanceEntity maintenanceEntity) {
        MaintenanceResponseDTO response = new MaintenanceResponseDTO();
        response.setId(maintenanceEntity.getId());
        response.setDescription(maintenanceEntity.getDescription());
        response.setStartDate(maintenanceEntity.getStartDate());
        response.setEndDate(maintenanceEntity.getEndDate());
        return response;
    }

    public static List<MaintenanceResponseDTO> mapToMaintenanceResponseList(List<MaintenanceEntity> maintenances) {
        if (maintenances.isEmpty()) {
            return new ArrayList<>();
        }
        return maintenances.stream().map(MaintenanceMapper::mapToMaintenanceResponse).collect(Collectors.toList());
    }

    public static List<MaintenanceResponseDTO> mapToMaintenanceSummaryResponseList(List<MaintenanceEntity> maintenances) {
        if (maintenances.isEmpty()) {
            return new ArrayList<>();
        }
        return maintenances.stream().map(MaintenanceMapper::mapToMaintenanceSummaryResponse).collect(Collectors.toList());
    }
}
