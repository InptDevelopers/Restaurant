package com.example.usersservice.services;

import com.example.usersservice.entities.User;
import com.example.usersservice.exceptions.UserException;
import com.example.usersservice.repositories.UserRepository;
import com.example.usersservice.repositories.WaiterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final WaiterRepository waitersRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(User user){
        return userRepository.saveAndFlush(user);
    }
    public User getuser(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException("User with id"+id+"doesn't exist"));
    }
    public void deleteUser(Long id) throws UserException {
        userRepository.findById(id).orElseThrow(() -> new UserException("User with id"+id+"doesn't exist"));
        userRepository.deleteById(id);
    }
    public Page<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

}
