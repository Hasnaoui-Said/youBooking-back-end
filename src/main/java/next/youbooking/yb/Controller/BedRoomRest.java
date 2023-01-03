package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.BedRoom;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.BedRoomService;
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
@RequestMapping("${api.endpoint}/bed-room")
public class BedRoomRest {
    @Autowired
    BedRoomService bedRoomService;

    @GetMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> findByUuid(@RequestParam String uuid) {
        BedRoom byUuid = bedRoomService.findByUuid(uuid);
        ResponseObject<BedRoom> responseObject = new ResponseObject<>(true,
                "check address", byUuid);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/uuid")
    public ResponseEntity<ResponseObject<?>> findAllByHotelUuid(@RequestParam String uuid) {
        List<BedRoom> all = bedRoomService.findAllByHotelUuid(uuid);
        ResponseObject<List<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", all);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/uuid/request")
    public ResponseEntity<ResponseObject<?>> findAllByHotelUuid(@RequestParam String uuid,
                                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<BedRoom> all = bedRoomService.findAllByHotelUuid(uuid, PageRequest.of(page, size));
        ResponseObject<Page<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", all);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/typeRome/name")
    public ResponseEntity<ResponseObject<?>> findAllByTypeRoomName(@RequestParam String uuid) {
        List<BedRoom> bedRooms = bedRoomService.findAllByTypeRoomName(uuid);
        ResponseObject<List<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRooms);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/typeRoom/name/request")
    public ResponseEntity<ResponseObject<?>> findAllByTypeRoomName(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam String uuid) {
        Page<BedRoom> bedRooms = bedRoomService.findAllByTypeRoomName(PageRequest.of(page, size), uuid);
        ResponseObject<Page<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRooms);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/name")
    public ResponseEntity<ResponseObject<?>> findAllByHotelName(@RequestParam String name) {
        List<BedRoom> bedRooms = bedRoomService.findAllByHotelName(name);
        ResponseObject<List<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRooms);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/name/request")
    public ResponseEntity<ResponseObject<?>> findAllByHotelName(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam String name) {
        Page<BedRoom> bedRooms = bedRoomService.findAllByHotelName(PageRequest.of(page, size), name);
        ResponseObject<Page<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRooms);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/uuid")
    public ResponseEntity<ResponseObject<?>> deleteByUuid(@RequestParam String uuid) {
        int res = bedRoomService.deleteByUuid(uuid);
        boolean success = true;
        String message = "BedRoom Delete successfully";
        if (res != 1){
            success = false;
            message = "This bedroom with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRoomService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        Page<BedRoom> bedRooms = bedRoomService.findAll(pageable);
        ResponseObject<Page<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRooms);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/request")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<BedRoom> bedRooms = bedRoomService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<BedRoom>> responseObject = new ResponseObject<>(true,
                "check address", bedRooms);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody BedRoom bedRoom) {
        try {
            BedRoom create = bedRoomService.save(bedRoom);
            ResponseObject<BedRoom> responseObject = new ResponseObject<>(true,
                    "BedRoom create successfully",create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<BedRoom> responseObject = new ResponseObject<>(false,
                    e.getMessage(),bedRoom);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody BedRoom bedRoom) {
        try {
            BedRoom create = bedRoomService.update(bedRoom);
            ResponseObject<BedRoom> responseObject = new ResponseObject<>(true,
                    "BedRoom update successfully",create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<BedRoom> responseObject = new ResponseObject<>(false,
                    e.getMessage(),bedRoom);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

}
