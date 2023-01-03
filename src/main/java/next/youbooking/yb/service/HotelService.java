package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {
    Hotel findByName(String name);

    Hotel findByStateHotel(String stateHotel);

    Hotel findByAddress(String address);

    int deleteByName(String name);

    Hotel findByUuid(String uuid);

    int deleteByUuid(String uuid);

    List<Hotel> findAllByUserUsername(String username);

    Page<Hotel> findAllByUserUsername(String username, PageRequest pageRequest);

    List<Hotel> findAll();

    Page<Hotel> findAll(Pageable pageable);

    Page<Hotel> findAll(PageRequest pageRequest);

    Hotel save(Hotel hotel);

    Hotel findByCode(String code);


    Hotel update(Hotel country);

    boolean existsByName(String name);

    boolean existsByUuid(String code);
}
