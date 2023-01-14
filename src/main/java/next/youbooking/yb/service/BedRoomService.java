package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.BedRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BedRoomService {
    BedRoom findByUuid(String uuid);

    List<BedRoom> findAllByHotelUuid(String uuid);

    Page<BedRoom> findAllByHotelUuid(String uuid, PageRequest request);

    List<BedRoom> findAllByTypeRoomName(String uuid);

    Page<BedRoom> findAllByTypeRoomName(PageRequest pageRequest, String uuid);

    List<BedRoom> findAllByHotelName(String name);

    Page<BedRoom> findAllByHotelName(PageRequest pageRequest, String name);

    int deleteByUuid(String uuid);

    List<BedRoom> findAll();

    Page<BedRoom> findAll(Pageable pageable);

    Page<BedRoom> findAll(PageRequest pageRequest);

    BedRoom save(BedRoom bedRoom);

    BedRoom update(BedRoom bedRoom);

    BedRoom saveBedRoom(BedRoom bedRoom, UUID uuid);
}
