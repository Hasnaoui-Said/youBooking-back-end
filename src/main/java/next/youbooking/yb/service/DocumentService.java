package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Document;

import java.util.List;
import java.util.UUID;

public interface DocumentService {
    List<Document> findAll();

    Document findByUuid(UUID uuid);

    Document save(Document document);
}
