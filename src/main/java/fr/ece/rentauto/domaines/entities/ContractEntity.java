package fr.ece.rentauto.domaines.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contracts")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String signatureDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private boolean validity;

    @OneToOne(fetch = FetchType.LAZY)
    private BookingEntity booking;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    List<PaymentEntity> payments = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
