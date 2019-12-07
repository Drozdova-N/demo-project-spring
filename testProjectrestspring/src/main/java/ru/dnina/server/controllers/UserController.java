package ru.dnina.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnina.server.forms.UpdateUserFormImpl;
import ru.dnina.server.services.UserService;
import ru.dnina.server.transfer.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {



    @Autowired
    @Qualifier("userServiceImpl")
    UserService userServices;

    @GetMapping("/users")
    public List<UserDto> getUsers(){
       return userServices.findAll();
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("user-id") Long id){
        try {
            return ResponseEntity.ok(userServices.findUserById(id));
        }
        catch (IllegalArgumentException exp){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/users/{user-id}/update")
    public ResponseEntity<UserDto> updateUser(@PathVariable("user-id") Long id, @RequestBody UpdateUserFormImpl form){
        try {
            return ResponseEntity.ok(userServices.updateUser(id, form));
        }
        catch (IllegalArgumentException exp){
            return ResponseEntity.badRequest().build();
        }
    }
}
