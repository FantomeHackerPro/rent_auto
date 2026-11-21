package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.EquipmentEntity;
import fr.ece.rentauto.controllers.dtos.EquipmentRequestDTO;

import java.util.List;

public interface EquipmentService {
    List<EquipmentEntity> getEquipments();
    List<EquipmentEntity> getEquipments(List<Long> equipmentIds);
    EquipmentEntity getEquipmentById(Long id) throws AppException;
    void addEquipment(EquipmentRequestDTO request);
    void updateEquipment(Long id, EquipmentRequestDTO request) throws AppException;
    void deleteEquipment(Long id);
}
