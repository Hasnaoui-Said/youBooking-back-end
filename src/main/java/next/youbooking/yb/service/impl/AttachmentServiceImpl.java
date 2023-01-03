package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.Attachment;
import next.youbooking.yb.repository.AttachmentRep;
import next.youbooking.yb.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentRep attachmentRep;

    @Override
    public Attachment findByUuid(String uuid) {
        return attachmentRep.findByUuid(uuid);
    }

    @Override
    public List<Attachment> findAllByHotelUuid(String uuid) {
        return attachmentRep.findAllByHotelUuid(uuid);
    }

    @Override
    public List<Attachment> findAllByHotelName(String uuid) {
        return attachmentRep.findAllByHotelName(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return attachmentRep.deleteByUuid(uuid);
    }

    @Override
    public List<Attachment> findAll() {
        return attachmentRep.findAll();
    }

    @Override
    public Page<Attachment> findAll(PageRequest pageRequest) {
        return attachmentRep.findAll(pageRequest);
    }
    @Override
    public Page<Attachment> findAll(Pageable pageable) {
        return attachmentRep.findAll(pageable);
    }

    @Override
    public Attachment save(Attachment attachment) {
        return attachmentRep.save(attachment);
    }

    @Override
    public Attachment update(Attachment attachment) {
        return null;
    }

    @Override
    public boolean existsByTitle(String titre) {
        return attachmentRep.existsByTitle(titre);
    }
}
