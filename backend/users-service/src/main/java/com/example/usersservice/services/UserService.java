package com.example.usersservice.services;

import com.example.usersservice.entities.AppUser;
import com.example.usersservice.exceptions.UserException;
import com.example.usersservice.repositories.UserRepository;
import com.example.usersservice.repositories.WaiterRepository;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final WaiterRepository waitersRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public AppUser createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.saveAndFlush(user);
    }

    public AppUser getuser(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException("User with id" + id + "doesn't exist"));
    }

    public void deleteUser(Long id) throws UserException {
        userRepository.findById(id).orElseThrow(() -> new UserException("User with id" + id + "doesn't exist"));
        userRepository.deleteById(id);
    }

    public Page<AppUser> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public AppUser loadUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }
}
