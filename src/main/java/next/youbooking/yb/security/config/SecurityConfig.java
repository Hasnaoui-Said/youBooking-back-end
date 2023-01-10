package next.youbooking.yb.security.config;

import next.youbooking.yb.security.exception.AccessDeniedExceptionHandler;
import next.youbooking.yb.security.filter.JwtAuthenticationFilter;
import next.youbooking.yb.security.filter.JwtAuthorizationFilter;
import next.youbooking.yb.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable()
                .cors().disable()
//                .configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(Arrays.asList("http://localhost:9090/login"));
//                    config.setAllowedMethods(Arrays.asList("POST"));
//                    config.setAllowCredentials(true);
//                    config.setAllowedHeaders(Arrays.asList("Content-Type", "application/x-www-form-urlencoded"));
//                    return config;})
//                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()// Exception handler
                .exceptionHandling().accessDeniedHandler(new AccessDeniedExceptionHandler())
                // ->>>>>>>>>>>2 solution
                // 1- use antMatchers
                // 2- use annotation in application EnableGlobalMethodSecurity and any method PostAuthorize(hasAuth(''))
                .and().authorizeRequests()
                .antMatchers("/**/jeton/**").permitAll()
//                .antMatchers("/**/users/**").permitAll()
                // any request accused with authenticated
                .anyRequest().authenticated()
                // integrate filter to my app

                .and()
                .addFilter(corsFilter())
                .addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
                // notion middleware: filter second->  communique with other app as a passerelle
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin",
                "Content-Type", "Accept", "Authorization", "Origin,Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}