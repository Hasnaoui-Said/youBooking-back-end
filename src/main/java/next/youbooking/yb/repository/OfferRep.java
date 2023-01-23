package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Offer;
import next.youbooking.yb.models.enums.StatusOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRep extends JpaRepository<Offer, UUID> {
    Offer findByUuid(UUID uuid);
    int deleteByUuid(UUID uuid);

    List<Offer> findAllByHotelUuid(UUID uuid);
    List<Offer> findAllByHotelUserUsername(String uuid);
    List<Offer> findAllByStatus(StatusOffer status);
    Offer findByTitle(String title);
    int deleteByTitle(String title);

    boolean existsByTitle(String title);
}
