package next.youbooking.yb.Controller;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("${api.endpoint}/users")
public class UserRest {
    @Autowired
    UserService userService;

    @GetMapping("/uuid")
    public User findByUuid(@RequestParam(name = "uuid") UUID uuid) {
        return userService.findByUuid(uuid);
    }

    @DeleteMapping("/cin")
    public User findByCin(@RequestParam(name = "cin") String cin) {
        return userService.findByCin(cin);
    }

    @GetMapping("/email")
    public User findByEmail(@RequestParam(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @DeleteMapping("/uuid")
    public int deleteByUuid(@RequestParam(name = "uuid") UUID uuid) {
        return userService.deleteByUuid(uuid);
    }

    @DeleteMapping("/email")
    public ResponseEntity<ResponseObject<?>> deleteByEmail(@RequestParam(name = "email") String email) {
        int res = userService.deleteByEmail(email);
        boolean success = true;
        String message = "User Delete successfully";
        if (res != 1){
            success = false;
            message = "This User with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/username")
    public ResponseEntity<ResponseObject<?>> deleteByUsername(@RequestParam(name = "username") String username) {
        int res = userService.deleteByUsername(username);
        boolean success = true;
        String message = "User Delete successfully";
        if (res != 1){
            success = false;
            message = "This User with this parameter not found";
        }
        ResponseObject<?> responseObject = new ResponseObject<>(success,
                message, res);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<User>> responseObject = new ResponseObject<>(false,
                "Find all", userService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<ResponseObject<?>> findAll(Pageable pageable) {
        ResponseObject<Page<User>> responseObject = new ResponseObject<>(false,
                "Find all", userService.findAll(pageable));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject<?>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<User> cities = userService.findAll(PageRequest.of(page, size));
        ResponseObject<Page<User>> responseObject = new ResponseObject<>(false,
               "Find all", cities);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PutMapping("/") @ResponseBody
    public ResponseEntity<ResponseObject<?>> update(@RequestBody User user) {
        try {
            User save = userService.update(user);
            ResponseObject<User> responseObject = new ResponseObject<>(true,
                    "save user!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<User> responseObject = new ResponseObject<>(false,
                    e.getMessage(), user);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/state/{uuid}") @ResponseBody
    public ResponseEntity<ResponseObject<?>> changeState(@RequestBody String state, @PathVariable(name = "uuid") UUID uuid) {
        try {
            User save = userService.changeState(uuid, state);
            ResponseObject<User> responseObject = new ResponseObject<>(true,
                    "Update state from user!!", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<UUID> responseObject = new ResponseObject<>(false,
                    e.getMessage(), uuid);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exist/email")
    public ResponseEntity<ResponseObject<?>> existsByEmail(@RequestParam(name = "email") String email) {
        boolean exists = userService.existsByEmail(email);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check user", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/exist/username")
    public ResponseEntity<ResponseObject<?>> existsByUsername(@RequestParam(name = "username") String username) {
        boolean exists = userService.existsByUsername(username);
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "check user", exists);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
