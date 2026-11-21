package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.AgencyEntity;
import fr.ece.rentauto.controllers.dtos.AgencyRequestDTO;

import java.util.List;

public interface AgencyService {
    List<AgencyEntity> getAgencies();
    AgencyEntity getAgency(Long id) throws AppException;
    void addAgency(AgencyRequestDTO request);
    void updateAgency(Long id, AgencyRequestDTO request) throws AppException;
    void deleteAgency(Long id);
}
