package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public interface AttachmentService {
    public Attachment findByUuid(UUID uuid);

    public List<Attachment> findAllByHotelUuid(String uuid);

    public List<Attachment> findAllByHotelName(String uuid);

    public int deleteByUuid(String uuid);

    public List<Attachment> findAll();

    public Page<Attachment> findAll(PageRequest pageRequest);
    public Page<Attachment> findAll(Pageable pageable);

    public Attachment save(Attachment attachment);

    Attachment update(Attachment attachment);

    boolean existsByTitle(String titre);

    Attachment updateAttachment(UUID uuid, List<MultipartFile> images);

    Attachment saveAtt(String title, String description, UUID uuidHotel);
}
