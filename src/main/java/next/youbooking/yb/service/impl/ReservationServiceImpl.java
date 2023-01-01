package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.Reservation;
import next.youbooking.yb.repository.ReservationRep;
import next.youbooking.yb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRep reservationRep;

    @Override
    public Reservation findByUuid(String uuid) {
        return reservationRep.findByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return reservationRep.deleteByUuid(uuid);
    }

    @Override
    public boolean existsByUuid(String uuid) {
        return reservationRep.existsByUuid(uuid);
    }

    @Override
    public List<Reservation> findByBedRoom(String name) {
        return reservationRep.findAllByBedroomName(name);
    }

    @Override
    public List<Reservation> findAllByGuestUsername(String username) {
        return reservationRep.findAllByGuestUsername(username);
    }

    @Override
    public Page<Reservation> findAllByGuestUsername(String username, PageRequest pageRequest) {
        return reservationRep.findAllByGuestUsername(username, pageRequest);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRep.findAll();
    }

    @Override
    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRep.findAll(pageable);
    }
    @Override
    public Page<Reservation> findAll(PageRequest pageRequest) {
        return reservationRep.findAll(pageRequest);
    }

    @Override
    public Reservation save(Reservation entity) {
        return reservationRep.save(entity);
    }
}