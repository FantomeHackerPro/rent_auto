package fr.ece.rentauto.domaines.mappers;

import fr.ece.rentauto.domaines.entities.EquipmentEntity;
import fr.ece.rentauto.controllers.dtos.EquipmentRequestDTO;
import fr.ece.rentauto.controllers.dtos.EquipmentResponseDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EquipmentMapper {
    public static EquipmentEntity mapToEquipmentToAdd(EquipmentRequestDTO request) {
        EquipmentEntity equipment = new EquipmentEntity();
        equipment.setLabel(request.getLabel());
        equipment.setCreatedAt(LocalDateTime.now());
        return equipment;
    }

    public static EquipmentEntity mapToEquipmentToUpdate(EquipmentEntity equipment, EquipmentRequestDTO request) {
        equipment.setLabel(request.getLabel());
        equipment.setUpdatedAt(LocalDateTime.now());
        return equipment;
    }

    public static EquipmentResponseDTO  mapToEquipmentToResponse(EquipmentEntity equipment) {
        EquipmentResponseDTO response = new EquipmentResponseDTO();
        response.setId(equipment.getId());
        response.setLabel(equipment.getLabel());
        response.setLabel(equipment.getLabel());
        return response;
    }

    public static List<EquipmentResponseDTO> mapToEquipmentToResponseList(List<EquipmentEntity> equipments) {
        if (equipments.isEmpty()) {
            return new ArrayList<>();
        }
        return equipments.stream().map(EquipmentMapper::mapToEquipmentToResponse).collect(Collectors.toList());
    }

    public static List<EquipmentResponseDTO> mapToEquipmentToSummaryResponseList(Set<EquipmentEntity> equipments) {
        if (equipments.isEmpty()) {
            return new ArrayList<>();
        }
        return equipments.stream().map(EquipmentMapper::mapToEquipmentToResponse).collect(Collectors.toList());
    }
}
