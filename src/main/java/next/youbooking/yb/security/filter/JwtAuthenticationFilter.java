package next.youbooking.yb.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import next.youbooking.yb.security.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // apply when user send request to log in with username and password, before load user by username
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // recuperate login and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Stock data as object
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // authenticate
        return this.authenticationManager.authenticate(authenticationToken);
    }

    // authentication successfully use library auth0 to create token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        if (request.getServletPath().equals(JwtUtil.REFRESH_JETON))
            filterChain.doFilter(request, response);
        else {
            // result of authenticate authResult
            User user = (User) authResult.getPrincipal();
            // Crypto algo symmetric with une secret
            Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
            // create jeton web token : success token
            List<String> authority = user.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
            String jwtSuccessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRED_JETON))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim(JwtUtil.AUTHORITIES, authority)
                    .sign(algorithm);
            // create jeton web token : refresh token
            String jwtRefreshToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRED_JETON_REFRESH))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            Map<String, String> jeton = new HashMap<>();
            // a revoir refresh token
            jeton.put("successJeton", jwtSuccessToken);
            jeton.put("refreshJeton", jwtRefreshToken);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), jeton);
        }
    }
}
