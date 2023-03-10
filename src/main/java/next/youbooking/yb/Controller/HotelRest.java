package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Hotel;
import next.youbooking.yb.models.vo.HotelVo;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.HotelService;
import next.youbooking.yb.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("${api.endpoint}/hotels")
public class HotelRest {
    @Autowired
    HotelService hotelService;
    @Autowired
    TypeRoomService typeRoomService;

    @GetMapping("/state")
    public ResponseEntity<ResponseObject<Hotel>> findByStateHotel(@RequestParam(name = "state") String stateHotel) {
        ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                "find by name", hotelService.findByStateHotel(stateHotel));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<ResponseObject<Hotel>> findByAddress(@RequestParam(name = "address") String address) {
        ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                "find by name", hotelService.findByAddress(address));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/uuid")
    public ResponseEntity<ResponseObject<Hotel>> findByUuid(@RequestParam(name = "uuid") UUID uuid) {
        ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                "find by name", hotelService.findByUuid(uuid));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    @DeleteMapping("/uuid")
    public ResponseEntity<ResponseObject> deleteByUuid(@RequestParam(name = "uuid") String uuid) {
        int res = hotelService.deleteByUuid(uuid);
        boolean success = true;
        String message = "Hotel Delete successfully";
        if (res != 1) {
            success = false;
            message = "This Hotel with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/user/username")
    public ResponseEntity<ResponseObject<List<Hotel>>> findAllByUserUsername(@RequestParam(name = "username") String username) {
        ResponseObject<List<Hotel>> responseObject = new ResponseObject<>(true,
                "find by name", hotelService.findAllByUserUsername(username));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/user/username/page")
    public ResponseEntity<ResponseObject<Page<Hotel>>> findAllByUserUsername(@RequestParam(name = "username") String username,
                                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        ResponseObject<Page<Hotel>> responseObject = new ResponseObject<>(true,
                "find by name", hotelService.findAllByUserUsername(username, PageRequest.of(page, size)));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<ResponseObject<Hotel>> findByName(@RequestParam(name = "name") String name) {
        ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                "find by name", hotelService.findByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/name")
    public ResponseEntity<ResponseObject> deleteByName(@RequestParam(name = "name") String name) {
        int res = hotelService.deleteByName(name);
        boolean success = true;
        String message = "Hotel Delete successfully";
        if (res != 1) {
            success = false;
            message = "This Hotel with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<ResponseObject<Hotel>> findByCode(@RequestParam(name = "code") String code) {
        ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                "find by code", hotelService.findByCode(code));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<List<Hotel>>> findAll() {
        ResponseObject<List<Hotel>> responseObject = new ResponseObject<>(false,
                "Find all", hotelService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/principal")
    public ResponseEntity<ResponseObject<List<Hotel>>> getAll(Principal principal) {
//        this.typeRoomService.add();
        ResponseObject<List<Hotel>> responseObject = new ResponseObject<>(false,
                "Find all by principal", hotelService.findAll(principal.getName()));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<Page<Hotel>>> findAll(Pageable pageable) {
        ResponseObject<Page<Hotel>> responseObject = new ResponseObject<>(false,
                "Find all", hotelService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<Page<Hotel>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Hotel> hotels = hotelService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<Hotel>> responseObject = new ResponseObject<>(false,
                "Find all", hotels);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<Hotel>> save(@RequestBody Hotel hotel) {
        try {
            Hotel save = hotelService.save(hotel);
            ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                    "save hotel!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Hotel> responseObject = new ResponseObject<>(false,
                    e.getMessage(), hotel);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseObject<?>> saveHotel(@RequestBody HotelVo hotel, Principal principal) {
        try {
            Hotel save = hotelService.saveHotel(hotel, principal.getName());
            ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                    "save hotel!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<HotelVo> responseObject = new ResponseObject<>(false,
                    e.getMessage(), hotel);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<Hotel>> update(@RequestBody Hotel country) {
        try {
            Hotel update = hotelService.update(country);
            ResponseObject<Hotel> responseObject = new ResponseObject<>(true,
                    "Update hotel!!", update);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Hotel> responseObject = new ResponseObject<>(false,
                    e.getMessage(), country);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/code")
    public ResponseEntity<ResponseObject> existsByUuid(@RequestParam(name = "code") String code) {
        boolean exists = hotelService.existsByUuid(code);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check hotel", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/exist/name")
    public ResponseEntity<ResponseObject> existsByName(@RequestParam(name = "name") String name) {
        boolean exists = hotelService.existsByName(name);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check hotel", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
