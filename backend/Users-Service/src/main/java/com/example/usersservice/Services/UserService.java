package com.example.usersservice.Services;

import com.example.usersservice.entities.User;
import com.example.usersservice.entities.Waiter;
import com.example.usersservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
private final UserRepository userRepository;
public void save(User user){
    userRepository.save(user);
}
public List<User> getUsers(){
    return userRepository.findAll();
}
public  void saveuser(User user){
    userRepository.save(user);

}
public User getuser(Long id){
    return userRepository.findById(id).get();
}
public List<Waiter> getwaiters(){
    List<User> users=userRepository.findAll();
    List<Waiter> waiters=new ArrayList<>();
    for(int i=0;i<users.size();i++){
        if(users.get(i)instanceof Waiter){
            waiters.add((Waiter) users.get(i));
        }
    }
return  waiters;
}
}
