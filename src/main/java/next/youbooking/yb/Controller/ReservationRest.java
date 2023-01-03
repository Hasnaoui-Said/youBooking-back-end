package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Reservation;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.ReservationService;
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
@RequestMapping("${api.endpoint}/reservation")
public class ReservationRest {
    @Autowired
    ReservationService reservationService;


    @GetMapping("/bed-room")
    public ResponseEntity<ResponseObject<?>> findByBedRoom(@RequestParam(name = "name") String name) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by name", reservationService.findByBedRoom(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> findByUuid(@RequestParam(name = "uuid") String uuid) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by uuid", reservationService.findByUuid(uuid));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> deleteByUuid(@RequestParam(name = "uuid") String uuid) {
        int res = reservationService.deleteByUuid(uuid);
        boolean success = true;
        String message = "Reservation Delete successfully";
        if (res != 1) {
            success = false;
            message = "This reservation with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Reservation>> responseObject = new ResponseObject<>(false,
                "Find all", reservationService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<Reservation>> responseObject = new ResponseObject<>(false,
                "Find all", reservationService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Reservation> cities = reservationService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<Reservation>> responseObject = new ResponseObject<>(false,
                "Find all", cities);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/username/page")
    public ResponseEntity<ResponseObject<?>> findAllByGuestUsername(String username,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Reservation> cities = reservationService.findAllByGuestUsername(username, PageRequest.of(page, size));
        ResponseObject<Page<Reservation>> responseObject = new ResponseObject<>(false,
                "Find all", cities);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/username")
    public ResponseEntity<ResponseObject<?>> findAllByGuestUsername(String username) {
        List<Reservation> reservation = reservationService.findAllByGuestUsername(username);
        ResponseObject<List<Reservation>> responseObject = new ResponseObject<>(false,
                "Find all", reservation);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Reservation reservation) {
        try {
            Reservation save = reservationService.save(reservation);
            ResponseObject<Reservation> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Reservation> responseObject = new ResponseObject<>(false,
                    e.getMessage(), reservation);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Reservation reservation) {
        try {
            Reservation save = reservationService.update(reservation);
            ResponseObject<Reservation> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Reservation> responseObject = new ResponseObject<>(false,
                    e.getMessage(), reservation);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/uuid")
    public ResponseEntity<ResponseObject<?>> existsByUuid(@RequestParam(name = "uuid") String uuid) {
        boolean exists = reservationService.existsByUuid(uuid);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check reservation", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
