package com.example.usersservice.controllers;

import com.example.usersservice.clients.RestaurantRestClient;
import com.example.usersservice.entities.Admin;
import com.example.usersservice.entities.AppUser;
import com.example.usersservice.enums.Role;
import com.example.usersservice.models.Restaurant;
import com.example.usersservice.repositories.UserRepository;
import com.example.usersservice.services.UserFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final RestaurantRestClient restaurantRestClient;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String email,
            @RequestParam String password) {
        return loginUser(email, password, false);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AppUser user) {
        log.info("ismail is here and naama is not");
        AppUser existedUser = userRepository.findByEmail(user.getEmail());
        Map<String, String> message = new HashMap<>();
        if (existedUser != null)
            return new ResponseEntity<>(message.put("message", "email already exist"), HttpStatus.CONFLICT);

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser appUser = UserFactory.instantiateUser(user);
        AppUser savedUser = userRepository.save(appUser);
        log.info("user saved");
        return loginUser(user.getEmail(), password, true);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/verifyAdmin")
    public void validateAdmin() {
    }

    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")

    @GetMapping("/verifyClient")
    public void validateClient() {
    }


    public ResponseEntity<Map<String, Object>> loginUser(
            String email,
            String password,
            boolean firstTime) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        String subject = authentication.getName();
        String scope = authentication.getAuthorities()
                .stream().map(aut -> aut.getAuthority())
                .collect(Collectors.toList()).get(0);
        System.out.println(authentication.getAuthorities().toString());
        System.out.println(authentication);
        Map<String, Object> idToken = new HashMap<>();
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
<<<<<<< HEAD
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
=======
                .expiresAt(instant.plus(100, ChronoUnit.MINUTES))
>>>>>>> 673d086774178f37dac8bc8d16f07a98f5ab3da9
                .issuer("security-service")
                .claim("scope", scope)
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken", jwtAccessToken);

        AppUser user = userRepository.findByEmail(email);
        user.setPassword(null);
        idToken.put("user", user);
        log.info("token generated " + jwtAccessToken);
        // create restaurant
        if (user.getRole() == Role.ADMIN && firstTime) {
            Restaurant restaurant = restaurantRestClient.createRestaurant(Restaurant.builder().idOwner(user.getId()).build(), "Bearer "+jwtAccessToken);
            ((Admin)user).setIdRestaurant(restaurant.getId());
            userRepository.save(user);
        }
        return new ResponseEntity<>(idToken, HttpStatus.OK);
    }
}