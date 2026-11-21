package fr.ece.rentauto.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgencyRequestDTO {

    private String name;

    private String city;

    private String address;

    private String email;

    private String phone;

}
