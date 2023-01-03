package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.TypeRoom;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("${api.endpoint}/type-room")
public class TypeRoomRest {
    @Autowired
    TypeRoomService typeRoomService;

    @GetMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> findByUuid(@RequestParam(name = "uuid") String uuid) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by uuid", typeRoomService.findByUuid(uuid));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> deleteByUuid(@RequestParam(name = "uuid") String uuid) {
        int res = typeRoomService.deleteByUuid(uuid);
        boolean success = true;
        String message = "TypeRoom Delete successfully";
        if (res != 1) {
            success = false;
            message = "This typeRome with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<TypeRoom>> responseObject = new ResponseObject<>(false,
                "Find all", typeRoomService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<TypeRoom>> responseObject = new ResponseObject<>(false,
                "Find all", typeRoomService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<TypeRoom> cities = typeRoomService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<TypeRoom>> responseObject = new ResponseObject<>(false,
                "Find all", cities);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody TypeRoom typeRome) {
        try {
            TypeRoom save = typeRoomService.save(typeRome);
            ResponseObject<TypeRoom> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<TypeRoom> responseObject = new ResponseObject<>(false,
                    e.getMessage(), typeRome);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody TypeRoom typeRome) {
        try {
            TypeRoom save = typeRoomService.update(typeRome);
            ResponseObject<TypeRoom> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<TypeRoom> responseObject = new ResponseObject<>(false,
                    e.getMessage(), typeRome);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/uuid")
    public ResponseEntity<ResponseObject<?>> existsByUuid(@RequestParam(name = "uuid") String uuid) {
        boolean exists = typeRoomService.existsByUuid(uuid);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check typeRome", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/exist/name")
    public ResponseEntity<ResponseObject<?>> existsByName(@RequestParam(name = "name") String name) {
        boolean exists = typeRoomService.existsByName(name);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check typeRome", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
