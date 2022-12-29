package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.BedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BedRoomRepository extends JpaRepository<BedRoom, UUID> {

}
