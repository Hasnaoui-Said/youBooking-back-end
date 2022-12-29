package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface HotelRepository extends JpaRepository<Hotel, UUID> {

}
