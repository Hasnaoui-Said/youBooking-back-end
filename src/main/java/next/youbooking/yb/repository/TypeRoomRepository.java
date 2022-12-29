package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TypeRoomRepository extends JpaRepository<TypeRoom, UUID> {

}
