package fr.ece.rentauto.services;

import fr.ece.rentauto.controllers.dtos.ContractRequestDTO;
import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.ContractEntity;

import java.util.List;

public interface ContractService {
    List<ContractEntity> getContracts();
    ContractEntity getContractById(Long id) throws AppException;
    void addAgency(ContractRequestDTO request);
    void updateAgency(Long id, ContractRequestDTO request) throws AppException;
    void deleteAgency(Long id);
}
