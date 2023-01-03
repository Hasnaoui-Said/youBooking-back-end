package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.City;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.CityService;
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
@RequestMapping("${api.endpoint}/city")
public class CityRest {
    @Autowired
    CityService cityService;

    @GetMapping("/name")
    public ResponseEntity<ResponseObject<?>> findByName(@RequestParam(name = "name") String name) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by name", cityService.findByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/name")
    public ResponseEntity<ResponseObject<?>> deleteByName(@RequestParam(name = "name") String name) {
        int res = cityService.deleteByName(name);
        boolean success = true;
        String message = "City Delete successfully";
        if (res != 1){
            success = false;
            message = "This city with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<ResponseObject<?>> findByZipCode(@RequestParam(name = "code") String code) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by zip code", cityService.findByZipCode(code));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/code")
    public ResponseEntity<ResponseObject<?>> deleteByZipCode(@RequestParam(name = "code") String code) {
        int res = cityService.deleteByZipCode(code);
        boolean success = true;
        String message = "City Delete successfully";
        if (res != 1){
            success = false;
            message = "This city with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/country/name")
    public ResponseEntity<ResponseObject<?>> findByCountryName(@RequestParam(name = "name") String name) {
        ResponseObject<List<City>> responseObject = new ResponseObject<>(false,
                "Find all", cityService.findByCountryName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/name/page")
    public ResponseEntity<ResponseObject<?>> findByCountryName(@RequestParam(name = "name") String name,
                                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        ResponseObject<Page<City>> responseObject = new ResponseObject<>(false,
                "Find all", cityService.findByCountryName(name, PageRequest.of(page, size)));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<City>> responseObject = new ResponseObject<>(false,
                "Find all", cityService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<City>> responseObject = new ResponseObject<>(false,
                "Find all", cityService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<City> cities = cityService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<City>> responseObject = new ResponseObject<>(false,
               "Find all", cities);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody City city) {
        try {
            City save = cityService.save(city);
            ResponseObject<City> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<City> responseObject = new ResponseObject<>(false,
                    e.getMessage(), city);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody City city) {
        try {
            City save = cityService.update(city);
            ResponseObject<City> responseObject = new ResponseObject<>(true,
                    "find all!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<City> responseObject = new ResponseObject<>(false,
                    e.getMessage(), city);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/code")
    public ResponseEntity<ResponseObject<?>> existsByCode(@RequestParam(name = "code") String code) {
        boolean exists = cityService.existsByCode(code);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check city", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/exist/name")
    public ResponseEntity<ResponseObject<?>> existsByName(@RequestParam(name = "name") String name) {
        boolean exists = cityService.existsByName(name);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check city", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
