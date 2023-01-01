package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.Hotel;
import next.youbooking.yb.repository.HotelRep;
import next.youbooking.yb.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRep hotelRep;

    @Override
    public Hotel findByName(String name) {
        return hotelRep.findByName(name);
    }

    @Override
    public Hotel findByStateHotel(String stateHotel) {
        return hotelRep.findByStateHotel(stateHotel);
    }

    @Override
    public Hotel findByAddress(String address) {
        return hotelRep.findByAddress(address);
    }

    @Override
    public int deleteByName(String name) {
        return hotelRep.deleteByName(name);
    }

    @Override
    public Hotel findByUuid(String uuid) {
        return hotelRep.findByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return hotelRep.deleteByUuid(uuid);
    }

    @Override
    public List<Hotel> findAllByUserUsername(String username) {
        return hotelRep.findAllByUserUsername(username);
    }

    @Override
    public Page<Hotel> findAllByUserUsername(String username, PageRequest pageRequest) {
        return hotelRep.findAllByUserUsername(username, pageRequest);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRep.findAll();
    }

    @Override
    public Page<Hotel> findAll(Pageable pageable) {
        return hotelRep.findAll(pageable);
    }
    @Override
    public Page<Hotel> findAll(PageRequest pageRequest) {
        return hotelRep.findAll(pageRequest);
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRep.save(hotel);
    }
}