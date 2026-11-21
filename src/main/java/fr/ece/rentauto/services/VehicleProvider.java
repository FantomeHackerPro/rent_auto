package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.VehicleRequestDTO;
import fr.ece.rentauto.domaines.entities.VehicleEntity;
import fr.ece.rentauto.domaines.mappers.VehicleMapper;
import fr.ece.rentauto.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VehicleProvider implements VehicleService {
    private VehicleRepository vehicleRepository;
    private AgencyService agencyService;
    private EquipmentService equipmentService;

    @Override
    public List<VehicleEntity> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleEntity getVehicle(Long id) throws AppException {
        return vehicleRepository.findById(id).orElseThrow(() -> new AppException("Vehicle not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addVehicle(VehicleRequestDTO request) throws AppException {
        VehicleEntity VehicleToAdd = VehicleMapper.mapToVehicleToAdd(request);
        VehicleToAdd.setAgency(this.agencyService.getAgency(request.getAgencyId()));
        VehicleToAdd.addEquipments(this.equipmentService.getEquipments(request.getEquipmentIds()));
        vehicleRepository.save(VehicleToAdd);
    }

    @Transactional
    @Override
    public void updateVehicle(Long id, VehicleRequestDTO request) throws AppException {
        VehicleEntity VehicleToUpdate = VehicleMapper.mapToVehicleToUpdate(this.getVehicle(id), request);
        VehicleToUpdate.setAgency(this.agencyService.getAgency(request.getAgencyId()));

        vehicleRepository.save(VehicleToUpdate);
    }

    @Transactional
    @Override
    public void deleteVehicle(Long id) {

    }
}
