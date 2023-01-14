package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentRep extends JpaRepository<Attachment, UUID> {

    Attachment findByUuid(UUID uuid);

    List<Attachment> findAllByHotelUuid(String uuid);

    List<Attachment> findAllByHotelName(String uuid);

    int deleteByUuid(String uuid);

    boolean existsByTitle(String titre);
}
