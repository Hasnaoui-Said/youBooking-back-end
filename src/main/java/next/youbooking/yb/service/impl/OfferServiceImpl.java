package next.youbooking.yb.service.impl;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Hotel;
import next.youbooking.yb.models.entity.Offer;
import next.youbooking.yb.models.enums.StatusOffer;
import next.youbooking.yb.models.vo.OfferStateVo;
import next.youbooking.yb.repository.OfferRep;
import next.youbooking.yb.service.HotelService;
import next.youbooking.yb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRep offerRep;
    @Autowired
    HotelService hotelService;

    @Override
    public Offer findByUuid(UUID uuid) {
        return offerRep.findByUuid(uuid);
    }

    @Override
    public int deleteByUuid(UUID uuid) {
        return offerRep.deleteByUuid(uuid);
    }

    @Override
    public List<Offer> findAllByHotelUuid(UUID uuid) {
        return offerRep.findAllByHotelUuid(uuid);
    }

    @Override
    public List<Offer> findAllByStatus(String status) {
        return offerRep.findAllByStatus(StatusOffer.valueOf(status));
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
        return this.offerRep.findAll();
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
    public Offer save(Offer offer) {
        return offerRep.save(offer);
    }

    @Override
    public Offer save(String title, String description, String uuidHotel) {
        Offer offer = new Offer();
        Hotel hotel = this.hotelService.findByUuid(UUID.fromString(uuidHotel));
        if (hotel == null)
            throw new BadRequestException("Hotel with this parameter uuid = " + uuidHotel + " not found");
        if (this.existsByTitle(title))
            throw new BadRequestException("Offer with this title uuid = " + uuidHotel + " not found");
        offer.setHotel(hotel);
        offer.setUuid(UUID.randomUUID());
        offer.setTitle(title);
        offer.setStatus(StatusOffer.PENDING);
        if (description.length() > 255) {
            offer.setDescription(description.substring(0, 254));
            offer.setSub_description(description.substring(254));
        } else
            offer.setDescription(description);
        return this.offerRep.save(offer);
    }

    @Override
    public List<Offer> findAllByHotelUserUsername(String uuid) {
        return offerRep.findAllByHotelUserUsername(uuid);
    }

    @Override
    public Offer update(Offer offer) {
        return null;
    }

    @Override
    public boolean existsByTitle(String title) {
        return offerRep.existsByTitle(title);
    }

    @Override
    public List<Offer> findAll(String name) {
        List<Hotel> hotels = this.hotelService.findAllByUserUsername(name);
        List<Offer> allOffers = new ArrayList<>();
        if (hotels == null)
            return allOffers;
        hotels.forEach(hotel -> {
            List<Offer> offers = this.findAllByHotelUuid(hotel.getUuid());
            allOffers.addAll(offers);
        });
        return allOffers;
    }

    @Override
    @Transactional
    public List<Offer> changeState(OfferStateVo offerStateVo) {
        List<Offer> offers = new ArrayList<>();
        offerStateVo.getUuidList().forEach(uuid -> {
            Offer offer = this.findByUuid(uuid);
            offer.setStatus(StatusOffer.valueOf(offerStateVo.getState()));
            offers.add(offer);
        });
        return offers;
    }

    @Override
    public List<Offer> getOffersAccepted() {
        return this.findAllByStatus("ACCEPTED");
    }
}