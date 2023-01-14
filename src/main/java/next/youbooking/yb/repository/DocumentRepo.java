package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentRepo extends JpaRepository<Document, UUID> {
    Document findByUuid(UUID uuid);
}
