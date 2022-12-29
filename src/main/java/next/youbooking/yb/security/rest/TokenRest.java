package next.youbooking.yb.security.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.service.UserDetailsServiceImpl;
import next.youbooking.yb.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @GetMapping("/refresh")
    public void refreshJeton(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String auth = request.getHeader(JwtUtil.AUTH_HEADER);
        if (auth != null && auth.startsWith(JwtUtil.BEARER)){
            try {
                String jwt = auth.substring(JwtUtil.BEARER.length());
                Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                User user = userDetailsService.findByUsername(username);

                List <String> authorities = user.getRoles().stream()
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

            }catch (Exception e){
                response.setHeader("error-message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

        }else {
            throw new RuntimeException("Refresh token required");
        }
    }


    @GetMapping("/profile")
    public User getProfile(Principal principal) {
        return userDetailsService.findByUsername(principal.getName());
    }
}
