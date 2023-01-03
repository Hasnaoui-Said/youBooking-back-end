package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfferService {
    Offer findByUuid(String uuid);

    int deleteByUuid(String uuid);

    List<Offer> findAllByHotelUuid(String uuid);

    List<Offer> findAllByStatus(String status);

    Offer findByTitle(String title);

    int deleteByTitle(String title);

    List<Offer> findAll();

    Page<Offer> findAll(Pageable pageable);

    Page<Offer> findAll(PageRequest pageRequest);

    Offer save(Offer offer);

    Offer update(Offer offer);

    boolean existsByTitle(String title);
}
