package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CityRep extends JpaRepository<City, UUID> {

}
