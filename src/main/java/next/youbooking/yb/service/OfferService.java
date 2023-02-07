package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Offer;
import next.youbooking.yb.models.vo.OfferStateVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    Offer findByUuid(UUID uuid);

    int deleteByUuid(UUID uuid);

    List<Offer> findAllByHotelUuid(UUID uuid);

    List<Offer> findAllByStatus(String status);

    Offer findByTitle(String title);

    int deleteByTitle(String title);

    List<Offer> findAll();

    Page<Offer> findAll(Pageable pageable);

    Page<Offer> findAll(PageRequest pageRequest);

    Offer save(Offer offer);
    Offer save(String title, String description, String uuidHotel);

    List<Offer> findAllByHotelUserUsername(String uuid);

    Offer update(Offer offer);

    boolean existsByTitle(String title);

    List<Offer> findAll(String name);
    List<Offer> changeState(OfferStateVo offerStateVo);

    List<Offer> getOffersAccepted();
}
