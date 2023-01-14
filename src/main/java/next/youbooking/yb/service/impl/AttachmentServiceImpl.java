package next.youbooking.yb.service.impl;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.exception.ServiceUnavailableException;
import next.youbooking.yb.models.entity.Attachment;
import next.youbooking.yb.models.entity.Document;
import next.youbooking.yb.models.entity.Hotel;
import next.youbooking.yb.repository.AttachmentRep;
import next.youbooking.yb.service.AttachmentService;
import next.youbooking.yb.service.DocumentService;
import next.youbooking.yb.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentRep attachmentRep;
    @Autowired
    DocumentService documentService;
    @Autowired
    HotelService hotelService;

    @Override
    public Attachment findByUuid(UUID uuid) {
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

    @Override
    public Attachment updateAttachment(UUID uuid, List<MultipartFile> images) {
        Attachment attachment = this.findByUuid(uuid);
        if (attachment == null)
            throw new BadRequestException("Attachment with this parameter not found!!");

        images.forEach(image->{
            Document document = new Document();
            try {
                document.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            document.setAttachment(attachment);
            document = this.documentService.save(document);
            attachment.getDocuments().add(document);
        });
        return attachment;
    }

    @Override
    public Attachment saveAtt(String title, String description, UUID uuidHotel) {
        Attachment attachment = new Attachment();
        attachment.setTitle(title);
        attachment.setDescription(description);
        attachment.setUuid(UUID.randomUUID());
        Hotel hotel = this.hotelService.findByUuid(uuidHotel);
        if (hotel == null)
            throw new ServiceUnavailableException("Hotel not found with UUID: " + uuidHotel);
        attachment.setHotel(hotel);
        return this.attachmentRep.save(attachment);
    }
}
