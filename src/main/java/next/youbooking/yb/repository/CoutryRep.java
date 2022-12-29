package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CoutryRep extends JpaRepository<Country, UUID> {

}
