package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.MaintenanceEntity;
import fr.ece.rentauto.controllers.dtos.MaintenanceRequestDTO;
import fr.ece.rentauto.domaines.mappers.MaintenanceMapper;
import fr.ece.rentauto.repositories.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class MaintenanceProvider implements MaintenanceService{
    private MaintenanceRepository maintenanceRepository;
    private VehicleService vehicleService;

    @Override
    public List<MaintenanceEntity> getMaintenances() {
        return this.maintenanceRepository.findAll();
    }

    @Override
    public MaintenanceEntity getMaintenanceById(Long id) throws AppException {
        return this.maintenanceRepository.findById(id).orElseThrow(() -> new AppException("Maintenance not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addMaintenance(MaintenanceRequestDTO request) throws AppException {
        MaintenanceEntity maintenanceToAdd = MaintenanceMapper.mapToMaintenanceToAdd(request);
        maintenanceToAdd.setVehicle(this.vehicleService.getVehicle(request.getVehicleId()));
        this.maintenanceRepository.save(maintenanceToAdd);
    }

    @Transactional
    @Override
    public void updateMaintenance(Long id, MaintenanceRequestDTO request) throws AppException {
        MaintenanceEntity maintenanceToUpdate = MaintenanceMapper.mapToMaintenanceToUpdate(this.getMaintenanceById(id), request);
        maintenanceToUpdate.setVehicle(this.vehicleService.getVehicle(request.getVehicleId()));
        this.maintenanceRepository.save(maintenanceToUpdate);
    }

    @Transactional
    @Override
    public void deleteMaintenance(Long id) {

    }
}
