package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.Document;
import next.youbooking.yb.repository.DocumentRepo;
import next.youbooking.yb.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentRepo documentRepo;

    @Override
    public List<Document> findAll() {
        return documentRepo.findAll();
    }

    @Override
    public Document findByUuid(UUID uuid) {
        return documentRepo.findByUuid(uuid);
    }

    @Override
    public Document save(Document document) {
        document.setUuid(UUID.randomUUID());
        document.setType("image/*");
        return documentRepo.save(document);
    }
}
