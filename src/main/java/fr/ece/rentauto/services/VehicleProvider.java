package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.controllers.dtos.VehicleRequestDTO;
import fr.ece.rentauto.domaines.entities.VehicleEntity;
import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
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
    private FileStorageService fileStorageService;

    @Override
    public List<VehicleEntity> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<VehicleEntity> getVehicles(VehicleStatus status) {
        return vehicleRepository.findAllByStatus(status);
    }

    @Override
    public VehicleEntity getVehicle(Long id) throws AppException {
        return vehicleRepository.findById(id).orElseThrow(() -> new AppException("Vehicle not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addVehicle(VehicleRequestDTO request) throws AppException {
        VehicleEntity vehicleToAdd = VehicleMapper.mapToVehicleToAdd(request);
        vehicleToAdd.setAgency(this.agencyService.getAgency(request.getAgencyId()));

        if (request.getEquipmentIds() != null && !request.getEquipmentIds().isEmpty()) {
            vehicleToAdd.addEquipments(this.equipmentService.getEquipments(request.getEquipmentIds()));
        }

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String imagePath = fileStorageService.saveVehicleImage(request.getImage());

            vehicleToAdd.setImageUrl(imagePath);
        }

        vehicleRepository.save(vehicleToAdd);
    }

    @Transactional
    @Override
    public void updateVehicle(Long id, VehicleRequestDTO request) throws AppException {
        VehicleEntity existingVehicle = this.getVehicle(id);
        String oldImageUrl = existingVehicle.getImageUrl();

        VehicleEntity vehicleToUpdate = VehicleMapper.mapToVehicleToUpdate(existingVehicle, request);
        vehicleToUpdate.setAgency(this.agencyService.getAgency(request.getAgencyId()));

        if (request.getEquipmentIds() != null && !request.getEquipmentIds().isEmpty()) {
            vehicleToUpdate.getEquipments().clear();
            vehicleToUpdate.addEquipments(this.equipmentService.getEquipments(request.getEquipmentIds()));
        }

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String newImagePath = fileStorageService.saveVehicleImage(request.getImage());
            vehicleToUpdate.setImageUrl(newImagePath); // Met à jour l'URL en base
            fileStorageService.deleteVehicleImage(oldImageUrl); // Supprime l'ancien fichier
        }

        vehicleRepository.save(vehicleToUpdate);
    }

    @Transactional
    @Override
    public void deleteVehicle(Long id) {

    }
}
