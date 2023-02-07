package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.BedRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BedRoomRep extends JpaRepository<BedRoom, UUID> {
    BedRoom findByUuid(String uuid);

    List<BedRoom> findAllByHotelUuid(String uuid);
    Page<BedRoom> findAllByHotelUuid(String uuid, PageRequest request);
    List<BedRoom> findAllByTypeRoomName(String uuid);

    Page<BedRoom> findAllByTypeRoomName(PageRequest pageRequest, String uuid);

    List<BedRoom> findAllByHotelName(String name);

    Page<BedRoom> findAllByHotelName(PageRequest pageRequest, String name);

    int deleteByUuid(String uuid);
}
