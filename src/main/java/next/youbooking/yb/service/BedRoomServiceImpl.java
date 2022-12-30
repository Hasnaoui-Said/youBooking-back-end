package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.BedRoom;
import next.youbooking.yb.repository.BedRoomRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedRoomServiceImpl {
    @Autowired
    BedRoomRep bedRoomRep;

    public BedRoom findByUuid(String uuid) {
        return bedRoomRep.findByUuid(uuid);
    }

    public List<BedRoom> findAllByHotelUuid(String uuid) {
        return bedRoomRep.findAllByHotelUuid(uuid);
    }

    public Page<BedRoom> findAllByHotelUuid(String uuid, PageRequest request) {
        return bedRoomRep.findAllByHotelUuid(uuid, request);
    }

    public List<BedRoom> findAllByTypeRoomName(String uuid) {
        return bedRoomRep.findAllByTypeRoomName(uuid);
    }

    public Page<BedRoom> findAllByTypeRoomName(PageRequest pageRequest, String uuid) {
        return bedRoomRep.findAllByTypeRoomName(pageRequest, uuid);
    }

    public List<BedRoom> findAllByHotelName(String name) {
        return bedRoomRep.findAllByHotelName(name);
    }

    public Page<BedRoom> findAllByHotelName(PageRequest pageRequest, String name) {
        return bedRoomRep.findAllByHotelName(pageRequest, name);
    }

    public int deleteByUuid(String uuid) {
        return bedRoomRep.deleteByUuid(uuid);
    }

    public List<BedRoom> findAll() {
        return bedRoomRep.findAll();
    }

    public Page<BedRoom> findAll(Pageable pageable) {
        return bedRoomRep.findAll(pageable);
    }

    public BedRoom save(BedRoom bedRoom) {
        return bedRoomRep.save(bedRoom);
    }
}