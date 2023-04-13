package com.test.lambdacrud.service;

import com.test.lambdacrud.dto.UserDTO;
import com.test.lambdacrud.entity.User;
import com.test.lambdacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> list(){
        return userRepository.findAll();
    }

    public User getUser(String userId){
        return userRepository.findById(userId).get();
    }

    public User saveUser(UserDTO userDto){
        User user = new User();
        user.setUserId(userDto.getId());
        user.setName(userDto.getName());
        return userRepository.save(user);
    }

    public User updateUser(UserDTO userDto){
        User user = userRepository.findById(userDto.getId()).get();;
        user.setUserId(userDto.getId());
        user.setName(userDto.getName());
        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public boolean existsName(String name){
        return userRepository.existsByName(name);
    }

    public boolean existsId(String userId){
        return userRepository.existsById(userId);
    }

}
