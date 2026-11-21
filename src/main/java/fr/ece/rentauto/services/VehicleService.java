package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.VehicleRequestDTO;
import fr.ece.rentauto.domaines.entities.VehicleEntity;

import java.util.List;

public interface VehicleService {
    List<VehicleEntity> getVehicles();
    VehicleEntity getVehicle(Long id) throws AppException;
    void addVehicle(VehicleRequestDTO request) throws AppException;
    void updateVehicle(Long id, VehicleRequestDTO request) throws AppException;
    void deleteVehicle(Long id);
}
