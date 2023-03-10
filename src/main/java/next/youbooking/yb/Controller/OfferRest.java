package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.Offer;
import next.youbooking.yb.models.vo.OfferStateVo;
import next.youbooking.yb.models.vo.OfferVo;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("${api.endpoint}/offer")
public class OfferRest {
    @Autowired
    OfferService offerService;

    @GetMapping("/title")
    public ResponseEntity<ResponseObject<?>> findByTitle(@RequestParam(name = "title") String title) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by name", offerService.findByTitle(title));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ResponseObject<?>> findByUuid(@PathVariable(name = "uuid") UUID uuid) {
        ResponseObject<?> responseObject = new ResponseObject<>(true,
                "find by uuid", offerService.findByUuid(uuid));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<ResponseObject<?>> deleteByUuid(@PathVariable(name = "uuid") UUID uuid) {
        int res = offerService.deleteByUuid(uuid);
        boolean success = true;
        String message = "Offer Delete successfully";
        if (res != 1) {
            success = false;
            message = "This Offer with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/title")
    public ResponseEntity<ResponseObject<?>> deleteByTitle(@PathVariable(name = "title") String title) {
        int res = offerService.deleteByTitle(title);
        boolean success = true;
        String message = "Offer Delete successfully";
        if (res != 1) {
            success = false;
            message = "This Offer with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/hotel/uuid")
    public ResponseEntity<ResponseObject<?>> findAllByHotelUuid(@RequestParam(name = "uuid") UUID uuid) {
        ResponseObject<List<Offer>> responseObject = new ResponseObject<>(false,
                "Find all", offerService.findAllByHotelUuid(uuid));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Offer>> responseObject = new ResponseObject<>(false,
                "Find all", offerService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> getOffersAccepted() {
        ResponseObject<List<Offer>> responseObject = new ResponseObject<>(false,
                "Find all offers", offerService.getOffersAccepted());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
//    @GetMapping("/principal/")
//    public ResponseEntity<ResponseObject<?>> findAllPrincipal(Principal principal) {
//        ResponseObject<List<Offer>> responseObject = new ResponseObject<>(false,
//                "Find all", offerService.findAll(principal.getName()));
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }

    @GetMapping("/status")
    public ResponseEntity<ResponseObject<?>> findAllByStatus(@RequestParam(name = "status") String status) {
        ResponseObject<List<Offer>> responseObject = new ResponseObject<>(false,
                "Find all", offerService.findAllByStatus(status));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<Offer>> responseObject = new ResponseObject<>(false,
                "Find all", offerService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Offer> offers = offerService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<Offer>> responseObject = new ResponseObject<>(false,
                "Find all", offers);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody OfferVo offerVo) {
        try {
            Offer save = offerService.save(offerVo.getTitle(), offerVo.getDescription(), offerVo.getHotel());
            ResponseObject<Offer> responseObject = new ResponseObject<>(true,
                    "save offer!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<OfferVo> responseObject = new ResponseObject<>(false,
                    e.getMessage(), offerVo);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/change-state")
    public ResponseEntity<ResponseObject<?>> changeState(@RequestBody OfferStateVo offerStateVo) {
        try {
            List<Offer> saves = offerService.changeState(offerStateVo);
            ResponseObject<List<Offer>> responseObject = new ResponseObject<>(true,
                    "save offer!!", saves);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<OfferStateVo> responseObject = new ResponseObject<>(false,
                    e.getMessage(), offerStateVo);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/principal")
    public ResponseEntity<ResponseObject<?>> findAllByHotelUserUsername(Principal principal) {
        ResponseObject<List<Offer>> responseObject = new ResponseObject<>(true,
                "Find all", offerService.findAllByHotelUserUsername(principal.getName()));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Offer offer) {
        try {
            Offer save = offerService.update(offer);
            ResponseObject<Offer> responseObject = new ResponseObject<>(true,
                    "update offer!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<Offer> responseObject = new ResponseObject<>(false,
                    e.getMessage(), offer);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/title")
    public ResponseEntity<ResponseObject<?>> existsByTitle(@RequestParam(name = "title") String title) {
        boolean exists = offerService.existsByTitle(title);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check offer", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
