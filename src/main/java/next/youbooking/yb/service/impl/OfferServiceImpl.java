package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.Offer;
import next.youbooking.yb.repository.OfferRep;
import next.youbooking.yb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRep offerRep;

    @Override
    public Offer findByUuid(String uuid) {
        return offerRep.findByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return offerRep.deleteByUuid(uuid);
    }

    @Override
    public List<Offer> findAllByHotelUuid(String uuid) {
        return offerRep.findAllByHotelUuid(uuid);
    }

    @Override
    public List<Offer> findAllByStatus(String status) {
        return offerRep.findAllByStatus(status);
    }

    @Override
    public Offer findByTitle(String title) {
        return offerRep.findByTitle(title);
    }

    @Override
    public int deleteByTitle(String title) {
        return offerRep.deleteByTitle(title);
    }

    @Override
    public List<Offer> findAll() {
        return offerRep.findAll();
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return offerRep.findAll(pageable);
    }
    @Override
    public Page<Offer> findAll(PageRequest pageRequest) {
        return offerRep.findAll(pageRequest);
    }

    @Override
    public <S extends Offer> S save(S entity) {
        return offerRep.save(entity);
    }
}