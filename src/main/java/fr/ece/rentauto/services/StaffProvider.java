package fr.ece.rentauto.services;

import fr.ece.rentauto.configs.AppException;
import fr.ece.rentauto.domaines.entities.StaffEntity;
import fr.ece.rentauto.controllers.dtos.StaffRequestDTO;
import fr.ece.rentauto.domaines.mappers.StaffMapper;
import fr.ece.rentauto.repositories.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StaffProvider implements StaffService{

    private StaffRepository staffRepository;
    private AgencyService agencyService;

    @Override
    public List<StaffEntity> getAllStaff() {
        return this.staffRepository.findAll();
    }

    @Override
    public StaffEntity getStaff(Long id) throws AppException {
        return this.staffRepository.findById(id).orElseThrow(() -> new AppException("Staff not found.", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public void addStaff(StaffRequestDTO request) throws AppException {
        StaffEntity staffToAdd = StaffMapper.mapToStaffToAdd(request);
        staffToAdd.setAgency(this.agencyService.getAgency(request.getAgencyId()));
        this.staffRepository.save(staffToAdd);
    }

    @Override
    public void updateStaff(Long id, StaffRequestDTO request) throws AppException {
        StaffEntity staffToUpdate = StaffMapper.mapToStaffToUpdate(this.getStaff(id), request);
        staffToUpdate.setAgency(this.agencyService.getAgency(request.getAgencyId()));
        this.staffRepository.save(staffToUpdate);
    }

    @Override
    public void deleteStaff(Long id) {

    }
}
