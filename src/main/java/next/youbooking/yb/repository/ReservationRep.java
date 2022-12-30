package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Address;
import next.youbooking.yb.models.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRep extends JpaRepository<Reservation, UUID> {
    Reservation findByUuid(String uuid);
    int deleteByUuid(String uuid);
    boolean existsByUuid(String uuid);
    Address findByHotelName(String name);
    List<Reservation> findAllByUserUsername(String username);
    Page<Reservation> findAllByUserUsername(String username, PageRequest pageRequest);
}
