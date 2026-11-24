package fr.ece.rentauto.domaines.entities;

import fr.ece.rentauto.domaines.enumerations.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleEntity vehicle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContractEntity contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
