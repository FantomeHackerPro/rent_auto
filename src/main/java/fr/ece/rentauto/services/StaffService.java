package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.StaffEntity;
import fr.ece.rentauto.controllers.dtos.StaffRequestDTO;

import java.util.List;

public interface StaffService {
    List<StaffEntity> getAllStaff();
    StaffEntity getStaff(Long id) throws AppException;
    void addStaff(StaffRequestDTO request) throws AppException;
    void updateStaff(Long id, StaffRequestDTO request) throws AppException;
    void deleteStaff(Long id);
}
