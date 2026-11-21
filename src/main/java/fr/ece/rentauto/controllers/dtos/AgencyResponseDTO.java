package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgencyResponseDTO {

    private Long id;

    private String name;

    private String city;

    private String address;

    private String email;

    private String phone;

    private List<StaffResponseDTO> staffs;

    private List<VehicleResponseDTO> vehicles;

}
