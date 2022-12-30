package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRep extends JpaRepository<Offer, UUID> {
    Offer findByUuid(String uuid);
    int deleteByUuid(String uuid);

    List<Offer> findAllByHotelUuid(String uuid);
    List<Offer> findAllByStatus(String status);
    Offer findByTitle(String title);
    int deleteByTitle(String title);
}
