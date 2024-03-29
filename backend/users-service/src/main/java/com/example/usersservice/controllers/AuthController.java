package com.example.usersservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

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
public class AuthController {
        private JwtEncoder jwtEncoder;
        private JwtDecoder jwtDecoder;
        private AuthenticationManager authenticationManager;
        private UserDetailsService userDetailsService;

        @PostMapping("/login")
        public ResponseEntity<Map<String, String>> jwtToken(
                        @RequestParam String email,
                        @RequestParam String password) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(email, password));
                String subject = authentication.getName();
                String scope = authentication.getAuthorities()
                                .stream().map(aut -> aut.getAuthority())
                                .collect(Collectors.toList()).get(0);
                System.out.println(authentication.getAuthorities().toString());
                System.out.println(authentication);
                Map<String, String> idToken = new HashMap<>();
                Instant instant = Instant.now();
                JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                                .subject(subject)
                                .issuedAt(instant)
                                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                                .issuer("security-service")
                                .claim("scope", scope)
                                .build();
                String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
                idToken.put("accessToken", jwtAccessToken);

                return new ResponseEntity<>(idToken, HttpStatus.OK);
        }

        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        @GetMapping("/verifyAdmin")
        public void validateAdmin() {
        }

        @PreAuthorize("hasAuthority('SCOPE_CLIENT')")

        @GetMapping("/verifyClient")
        public void validateClient() {
        }
}