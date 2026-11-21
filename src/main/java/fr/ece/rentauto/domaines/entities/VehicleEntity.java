package fr.ece.rentauto.domaines.entities;

import fr.ece.rentauto.domaines.enumerations.VehicleCategory;
import fr.ece.rentauto.domaines.enumerations.VehicleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "register_number", nullable = false, unique = true, length = 20)
    private String registerNumber;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleCategory category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyRate;

    private int year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleStatus status;

    @ManyToMany
    @JoinTable(
            name = "vehicle_equipment",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<EquipmentEntity> equipments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private AgencyEntity agency;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<MaintenanceEntity> maintenances = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void addEquipments(List<EquipmentEntity> equipments) {
        this.equipments.addAll(equipments);
//        equipments.forEach(equipment -> equipment.getVehicles().add(this));
    }
}
