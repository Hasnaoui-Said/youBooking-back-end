package next.youbooking.yb.security.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.security.models.domains.ResponseObject;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.service.UserDetailsServiceImpl;
import next.youbooking.yb.security.util.JwtUtil;
import next.youbooking.yb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${api.endpoint}/jeton")
public class TokenRest {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    UserService userService;

    @GetMapping("/refresh")
    public void refreshJeton(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String auth = request.getHeader(JwtUtil.AUTH_HEADER);
        if (auth != null && auth.startsWith(JwtUtil.BEARER)) {
            try {
                String jwt = auth.substring(JwtUtil.BEARER.length());
                Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                User user = userDetailsService.findByUsername(username);

                List<String> authorities = user.getRoles().stream()
                        .map(usersRoles -> usersRoles.getRole().getName()).collect(Collectors.toList());

                String jwtSuccessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRED_JETON))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("authority", authorities)
                        .sign(algorithm);
                String refreshJeton = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRED_JETON))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);
                Map<String, String> jeton = new HashMap<>();

                jeton.put("successJeton", jwtSuccessToken);
                jeton.put("refreshJeton", refreshJeton);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), jeton);

            } catch (Exception e) {
                response.setHeader("error-message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

        } else {
            throw new RuntimeException("Refresh token required");
        }
    }


    @GetMapping("/profile")
    public User getProfile(Principal principal) {
        return userDetailsService.findByUsername(principal.getName());
    }

    @PostMapping("/sign_in")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody User user, @RequestParam(name = "role") String role) {

        try {
            User save = userService.save(user, role);
            ResponseObject<User> responseObject = new ResponseObject<>(true,
                    "User saved successfully", save);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            System.out.println(this);
            System.out.println(e.getMessage());
            ResponseObject<User> responseObject = new ResponseObject<>(false,
                    e.getMessage(), user);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
