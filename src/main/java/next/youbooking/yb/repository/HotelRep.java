package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRep extends JpaRepository<Hotel, UUID> {

    Hotel findByName(String name);
    Hotel findByStateHotel(String stateHotel);
    Hotel findByAddress(String address);
    int deleteByName(String name);
    Hotel findByUuid(UUID uuid);
    int deleteByUuid(String uuid);
    List<Hotel> findAllByUserUsername(String username);
    Page<Hotel> findAllByUserUsername(String username, PageRequest pageRequest);

    boolean existsByName(String name);

    boolean existsByUuid(String uuid);

}
