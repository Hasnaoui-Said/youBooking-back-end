package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Attachment;
import next.youbooking.yb.repository.AttachmentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl{
    @Autowired
    AttachmentRep attachmentRep;

    public Attachment findByUuid(String uuid) {
        return attachmentRep.findByUuid(uuid);
    }

    public List<Attachment> findAllByHotelUuid(String uuid) {
        return attachmentRep.findAllByHotelUuid(uuid);
    }

    public List<Attachment> findAllByHotelName(String uuid) {
        return attachmentRep.findAllByHotelName(uuid);
    }

    public int deleteByUuid(String uuid) {
        return attachmentRep.deleteByUuid(uuid);
    }

    public List<Attachment> findAll() {
        return attachmentRep.findAll();
    }

    public Page<Attachment> findAll(Pageable pageable) {
        return attachmentRep.findAll(pageable);
    }

    public Attachment save(Attachment attachment) {
        return attachmentRep.save(attachment);
    }
}
