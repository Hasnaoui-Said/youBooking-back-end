package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.BedRoom;
import next.youbooking.yb.repository.BedRoomRep;
import next.youbooking.yb.service.BedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedRoomServiceImpl implements BedRoomService {
    @Autowired
    BedRoomRep bedRoomRep;

    @Override
    public BedRoom findByUuid(String uuid) {
        return bedRoomRep.findByUuid(uuid);
    }

    @Override
    public List<BedRoom> findAllByHotelUuid(String uuid) {
        return bedRoomRep.findAllByHotelUuid(uuid);
    }

    @Override
    public Page<BedRoom> findAllByHotelUuid(String uuid, PageRequest request) {
        return bedRoomRep.findAllByHotelUuid(uuid, request);
    }

    @Override
    public List<BedRoom> findAllByTypeRoomName(String uuid) {
        return bedRoomRep.findAllByTypeRoomName(uuid);
    }

    @Override
    public Page<BedRoom> findAllByTypeRoomName(PageRequest pageRequest, String uuid) {
        return bedRoomRep.findAllByTypeRoomName(pageRequest, uuid);
    }

    @Override
    public List<BedRoom> findAllByHotelName(String name) {
        return bedRoomRep.findAllByHotelName(name);
    }

    @Override
    public Page<BedRoom> findAllByHotelName(PageRequest pageRequest, String name) {
        return bedRoomRep.findAllByHotelName(pageRequest, name);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return bedRoomRep.deleteByUuid(uuid);
    }

    @Override
    public List<BedRoom> findAll() {
        return bedRoomRep.findAll();
    }

    @Override
    public Page<BedRoom> findAll(Pageable pageable) {
        return bedRoomRep.findAll(pageable);
    }

    @Override
    public Page<BedRoom> findAll(PageRequest pageRequest) {
        return bedRoomRep.findAll(pageRequest);
    }

    @Override
    public BedRoom save(BedRoom bedRoom) {
        return bedRoomRep.save(bedRoom);
    }

    @Override
    public BedRoom update(BedRoom bedRoom) {
        return null;
    }
}