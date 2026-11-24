package fr.ece.rentauto.domaines.entities;

import fr.ece.rentauto.domaines.enumerations.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StaffEntity staff;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CustomerEntity customer;

}
