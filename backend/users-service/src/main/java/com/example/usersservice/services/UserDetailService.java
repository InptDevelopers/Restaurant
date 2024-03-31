package com.example.usersservice.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.usersservice.entities.AppUser;

import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private UserService userService;

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userService.loadUserByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User with email " + email + " no found");
        GrantedAuthority role = new SimpleGrantedAuthority(user.getRole().toString());
        UserDetails userDetails = User.withUsername(email).password(user.getPassword()).authorities(role)
                .build();
        return userDetails;
    }

}
