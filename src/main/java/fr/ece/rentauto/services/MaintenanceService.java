package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.MaintenanceEntity;
import fr.ece.rentauto.controllers.dtos.MaintenanceRequestDTO;

import java.util.List;

public interface MaintenanceService {
    List<MaintenanceEntity> getMaintenances();
    MaintenanceEntity getMaintenanceById(Long id) throws AppException;
    void addMaintenance(MaintenanceRequestDTO request) throws AppException;
    void updateMaintenance(Long id, MaintenanceRequestDTO request) throws AppException;
    void deleteMaintenance(Long id);
}
