package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.AgencyEntity;
import fr.ece.rentauto.controllers.dtos.AgencyRequestDTO;
import fr.ece.rentauto.domaines.mappers.AgencyMapper;
import fr.ece.rentauto.repositories.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class AgencyProvider implements AgencyService {

    private AgencyRepository agencyRepository;

    @Override
    public List<AgencyEntity> getAgencies() {
        return this.agencyRepository.findAll();
    }

    @Override
    public AgencyEntity getAgency(Long id) throws AppException {
        return this.agencyRepository.findById(id).orElseThrow(() -> new AppException("Agency not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    @Override
    public void addAgency(AgencyRequestDTO request) {
        this.agencyRepository.save(AgencyMapper.mapToAgencyToAdd(request));
    }

    @Transactional
    @Override
    public void updateAgency(Long id, AgencyRequestDTO request) throws AppException {
        this.agencyRepository.save(AgencyMapper.mapToAgencyToUpdate(this.getAgency(id), request));
    }

    @Transactional
    @Override
    public void deleteAgency(Long id) {

    }
}
