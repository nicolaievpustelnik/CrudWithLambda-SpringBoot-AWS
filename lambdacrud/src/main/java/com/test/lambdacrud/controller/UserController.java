package com.test.lambdacrud.controller;

import com.test.lambdacrud.dto.UserDTO;
import com.test.lambdacrud.entity.User;
import com.test.lambdacrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Iterable<User>> list(){
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        if (!userService.existsId(userId)){
            return new ResponseEntity("Doesn't exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody UserDTO userDto){
        if(userService.existsId(userDto.getId()))
            return new ResponseEntity("The id already exists", HttpStatus.BAD_REQUEST);
        if(userService.existsName(userDto.getName()))
            return new ResponseEntity("The name already exists", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody UserDTO usuarioDto){
        if(!userService.existsId(usuarioDto.getId()))
            return new ResponseEntity("Doesn't exist", HttpStatus.NOT_FOUND);
        if(userService.existsName(usuarioDto.getName()) && !userService.getUser(usuarioDto.getId()).getName().equals(usuarioDto.getName()))
            return new ResponseEntity("The name already exists", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(userService.updateUser(usuarioDto));
    }

    @DeleteMapping("/user/{usuarioId}")
    public ResponseEntity<?> delete(@PathVariable("usuarioId") String usuarioId){
        if(!userService.existsId(usuarioId))
            return new ResponseEntity("Doesn't exist", HttpStatus.NOT_FOUND);
        userService.deleteUser(usuarioId);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }
}
