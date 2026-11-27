package fr.ece.rentauto.controllers.dtos;

import fr.ece.rentauto.domaines.enumerations.VehicleCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleRequestDTO {

    @NotBlank
    private String registerNumber;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private VehicleCategory category;

    @NotNull
    @Positive
    private BigDecimal dailyRate;

    @NotNull
    @Min(1990)
    private Integer year;

    @NotNull
    private Long agencyId;

    private List<Long> equipmentIds;

    private MultipartFile image;

}