package fr.ece.rentauto.repositories;

import fr.ece.rentauto.domaines.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
