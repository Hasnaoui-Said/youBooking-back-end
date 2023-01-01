package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Address;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("${api.endpoint}/address")
public class AddressRest {
    @Autowired
    AddressService addressService;

    @GetMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> findByUuid(@RequestParam(name = "uuid", defaultValue = "") String uuid) {
        Address byUuid = addressService.findByUuid(uuid);
        boolean success = true;
        if (byUuid == null) success = false;
        ResponseObject<Address> responseObject = new ResponseObject<>(success,
                "find all!!", byUuid);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/uuid")
    public ResponseEntity<ResponseObject<?>> deleteByUuid(@RequestParam(name = "uuid", defaultValue = "") String uuid) {
        int result = addressService.deleteByUuid(uuid);
        ResponseObject<Integer> responseObject = new ResponseObject<>(true,
                "find all!!", result);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/name")
    public ResponseEntity<ResponseObject<?>> findByHotelName(@RequestParam(name = "name", defaultValue = "") String name) {
        Address lists = addressService.findByHotelName(name);
        ResponseObject<Address> responseObject = new ResponseObject<>(true,
                "find all!!", lists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/city/name")
    public ResponseEntity<ResponseObject<?>> findAllByCityName(@RequestParam(name = "name", defaultValue = "") String name) {
        List<Address> lists = addressService.findAllByCityName(name);
        ResponseObject<List<Address>> responseObject = new ResponseObject<>(true,
                "find all!!", lists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Address> lists = addressService.findAll(Pageable.ofSize(size).withPage(page));
        ResponseObject<Page<Address>> responseObject = new ResponseObject<>(true,
                "find all!!", lists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        List<Address> lists = addressService.findAll();
        ResponseObject<List<Address>> responseObject = new ResponseObject<>(true,
                "find all!!", lists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Address address) {
        try {
            Address save = addressService.save(address);
            ResponseObject<Address> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Address> responseObject = new ResponseObject<>(false,
                    e.getMessage(), address);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist")
    public ResponseEntity<ResponseObject<?>> existsByAddress(@RequestParam(defaultValue = "", name = "address") String address) {
        boolean exists = addressService.existsByAddress(address);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check address", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
