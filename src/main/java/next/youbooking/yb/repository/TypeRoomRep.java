package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeRoomRep extends JpaRepository<TypeRoom, UUID> {
    TypeRoom findByUuid(String uuid);
    int deleteByUuid(String uuid);
}
