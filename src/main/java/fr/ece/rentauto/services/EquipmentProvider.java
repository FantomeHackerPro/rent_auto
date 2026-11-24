package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.EquipmentEntity;
import fr.ece.rentauto.controllers.dtos.EquipmentRequestDTO;
import fr.ece.rentauto.domaines.mappers.EquipmentMapper;
import fr.ece.rentauto.repositories.EquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class EquipmentProvider implements EquipmentService{

    private EquipmentRepository equipmentRepository;

    @Override
    public List<EquipmentEntity> getEquipments() {
        return this.equipmentRepository.findAll();
    }

    @Override
    public List<EquipmentEntity> getEquipments(List<Long> equipmentIds) {
        return this.equipmentRepository.findAllById(equipmentIds);
    }

    @Override
    public EquipmentEntity getEquipmentById(Long id) throws AppException {
        return this.equipmentRepository.findById(id).orElseThrow(() -> new AppException("Equipment not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addEquipment(EquipmentRequestDTO request) {
        this.equipmentRepository.save(EquipmentMapper.mapToEquipmentToAdd(request));
    }

    @Transactional
    @Override
    public void updateEquipment(Long id, EquipmentRequestDTO request) throws AppException {
        this.equipmentRepository.save(EquipmentMapper.mapToEquipmentToUpdate(this.getEquipmentById(id), request));
    }

    @Transactional
    @Override
    public void deleteEquipment(Long id) {

    }
}
