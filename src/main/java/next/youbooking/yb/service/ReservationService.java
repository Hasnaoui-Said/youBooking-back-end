package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    Reservation findByUuid(String uuid);

    int deleteByUuid(String uuid);

    boolean existsByUuid(String uuid);

    List<Reservation> findByBedRoom(String name);

    List<Reservation> findAllByGuestUsername(String username);

    Page<Reservation> findAllByGuestUsername(String username, PageRequest pageRequest);

    List<Reservation> findAll();

    Page<Reservation> findAll(Pageable pageable);

    Page<Reservation> findAll(PageRequest pageRequest);

    Reservation save(Reservation entity);

    Reservation update(Reservation reservation);
}
