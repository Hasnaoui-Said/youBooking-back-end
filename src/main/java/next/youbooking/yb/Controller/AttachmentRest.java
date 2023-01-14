package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Attachment;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("${api.endpoint}/attachment")
public class AttachmentRest {
    @Autowired
    AttachmentService attachmentService;

    @GetMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> findByUuid(@RequestParam UUID uuid) {
        Attachment attachment = attachmentService.findByUuid(uuid);
        ResponseObject<Attachment> responseObject = new ResponseObject<>(true,
                "find all!!", attachment);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/uuid")
    public ResponseEntity<ResponseObject<?>> findAllByHotelUuid(@RequestParam(name = "uuid", defaultValue = "") String uuid) {
        List<Attachment> attachments = attachmentService.findAllByHotelUuid(uuid);
        ResponseObject<List<Attachment>> responseObject = new ResponseObject<>(true,
                "find all!!", attachments);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/name")
    public ResponseEntity<ResponseObject<?>> findAllByHotelName(@RequestParam(name = "name", defaultValue = "") String name) {
        List<Attachment> attachments = attachmentService.findAllByHotelName(name);
        ResponseObject<List<Attachment>> responseObject = new ResponseObject<>(true,
                "find all!!", attachments);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> deleteByUuid(@RequestParam(name = "uuid", defaultValue = "") String uuid) {
        int res = attachmentService.deleteByUuid(uuid);
        boolean success = true;
        String message = "Attachment Delete successfully";
        if (res != 1) {
            success = false;
            message = "This Attachment with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        List<Attachment> attachments = attachmentService.findAll();
        ResponseObject<List<Attachment>> responseObject = new ResponseObject<>(true,
                "find all!!", attachments);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Attachment> attachments = attachmentService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<Attachment>> responseObject = new ResponseObject<>(true,
                "find all!!", attachments);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<Attachment>> responseObject = new ResponseObject<>(true,
                "find all!!", attachmentService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Attachment attachment) {
        try {
            Attachment save = attachmentService.save(attachment);
            ResponseObject<Attachment> responseObject = new ResponseObject<>(true,
                    "save!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Attachment> responseObject = new ResponseObject<>(false,
                    e.getMessage(), attachment);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/uuid/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject<?>> saveDocByUuidAttachment(@RequestPart List<MultipartFile> images, @RequestPart String uuid) {
        try {
            Attachment attachment = this.attachmentService.updateAttachment(UUID.fromString(uuid), images);
            ResponseObject<Attachment> responseObject = new ResponseObject<>(true,
                    "Attachment updated successfully", attachment);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<String> responseObject = new ResponseObject<>(false,
                    e.getMessage(), uuid);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Attachment attachment) {
        try {
            Attachment save = attachmentService.update(attachment);
            ResponseObject<Attachment> responseObject = new ResponseObject<>(true,
                    "update!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Attachment> responseObject = new ResponseObject<>(false,
                    e.getMessage(), attachment);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist")
    public ResponseEntity<ResponseObject<?>> existsByTitle(@RequestParam(name = "titre", defaultValue = "") String titre) {
        boolean exists = attachmentService.existsByTitle(titre);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check attachment", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
