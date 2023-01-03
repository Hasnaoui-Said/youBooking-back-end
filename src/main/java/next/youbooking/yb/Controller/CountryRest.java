package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Country;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.CountryService;
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
@RequestMapping("${api.endpoint}/country")
public class CountryRest {
    @Autowired
    CountryService countryService;

    @GetMapping("/name")
    public ResponseEntity<ResponseObject<?>> findByName(@RequestParam(name = "name") String name) {
        ResponseObject<Country> responseObject = new ResponseObject<>(true,
                "find by name", countryService.findByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/name")
    public ResponseEntity<ResponseObject<?>> deleteByName(@RequestParam(name = "name") String name) {
        int res = countryService.deleteByName(name);
        boolean success = true;
        String message = "Country Delete successfully";
        if (res != 1){
            success = false;
            message = "This country with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<ResponseObject<?>> findByCode(@RequestParam(name = "code") String code) {
        ResponseObject<Country> responseObject = new ResponseObject<>(true,
                "find by code", countryService.findByCode(code));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/code")
    public ResponseEntity<ResponseObject<?>> deleteByCode(@RequestParam(name = "code") String code) {
        int res = countryService.deleteByCode(code);
        boolean success = true;
        String message = "country Delete successfully";
        if (res != 1){
            success = false;
            message = "This country with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Country>> responseObject = new ResponseObject<>(false,
                "Find all", countryService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<Country>> responseObject = new ResponseObject<>(false,
                "Find all", countryService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Country> countries = countryService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<Country>> responseObject = new ResponseObject<>(false,
               "Find all", countries);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Country country) {
        try {
            Country save = countryService.save(country);
            ResponseObject<Country> responseObject = new ResponseObject<>(true,
                    "save country!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Country> responseObject = new ResponseObject<>(false,
                    e.getMessage(), country);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Country country) {
        try {
            Country update = countryService.update(country);
            ResponseObject<Country> responseObject = new ResponseObject<>(true,
                    "Update country!!", update);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Country> responseObject = new ResponseObject<>(false,
                    e.getMessage(), country);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/code")
    public ResponseEntity<ResponseObject<?>> existsByCode(@RequestParam(name = "code") String code) {
        boolean exists = countryService.existsByCode(code);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check country", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/exist/name")
    public ResponseEntity<ResponseObject<?>> existsByName(@RequestParam(name = "name") String name) {
        boolean exists = countryService.existsByName(name);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check country", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
