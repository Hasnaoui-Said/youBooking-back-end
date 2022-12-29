package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OfferRepository extends JpaRepository<Offer, UUID> {
    
}
