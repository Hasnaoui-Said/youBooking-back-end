package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AttachmentRep extends JpaRepository<Attachment, UUID> {

}
