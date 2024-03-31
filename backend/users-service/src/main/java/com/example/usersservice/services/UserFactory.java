package com.example.usersservice.services;


import com.example.usersservice.entities.*;
import com.example.usersservice.enums.Role;
import org.springframework.beans.BeanUtils;

public class UserFactory {
        public static AppUser instantiateUser(AppUser appUser){
        AppUser user = null ;
        if(appUser.getRole()== Role.ADMIN){

           user = new Admin();
           BeanUtils.copyProperties(appUser,user);
        }
        if(appUser.getRole()== Role.WAITER){
            user = new Waiter();
            BeanUtils.copyProperties(appUser,user);
        }
        if(appUser.getRole()== Role.CHEF){
            user = new Chef();
            BeanUtils.copyProperties(appUser,user);
        }
        if(appUser.getRole()== Role.CLIENT){
            user = new Client();
            BeanUtils.copyProperties(appUser,user);
        }
        return  user;
    }
}
