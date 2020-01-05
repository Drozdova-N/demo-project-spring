package ru.dnina.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.dnina.server.forms.UpdatePasswordForm;
import ru.dnina.server.forms.UpdateRoleForm;
import ru.dnina.server.forms.impl.UpdatePasswordFormImpl;
import ru.dnina.server.forms.impl.UpdateRoleFormImpl;
import ru.dnina.server.forms.impl.UpdateUserFormImpl;
import ru.dnina.server.services.UserService;
import ru.dnina.server.transfer.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {



    @Autowired
    @Qualifier("userServiceImpl")
    UserService userServices;

    @GetMapping("/users")
    public List<UserDto> getUsers(HttpServletRequest req, HttpServletResponse res) {
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

    @GetMapping("/users/authorized-user")
    public ResponseEntity<UserDto> getAuthorizedUser(){
        try {
           return ResponseEntity.ok(userServices.findAuthorizedUser());
        }
        catch (Exception exp){
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

    @PutMapping("/users/{user-id}/update-role")
    public ResponseEntity<UserDto> updateRoleUser(@PathVariable("user-id") Long id, @RequestBody UpdateRoleFormImpl form){
        try {
            return ResponseEntity.ok(userServices.updateRoleUser(id, form));
        }
        catch (IllegalArgumentException exp){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/users/{user-id}/update-pass")
    public ResponseEntity<Object> updatePasswordUser(@PathVariable("user-id") Long id, @RequestBody UpdatePasswordFormImpl form){
        try {
            userServices.updatePasswordUser(id, form);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
    }
}
