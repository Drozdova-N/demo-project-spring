package ru.dnina.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dnina.server.forms.SignUpFormImpl;
import ru.dnina.server.services.SignUpService;

@RestController
@RequestMapping("/api")
public class SignUpController {

    @Autowired
    @Qualifier("signUpServiceImpl")
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody SignUpFormImpl form){
        try {
            signUpService.signUp(form);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException exp){
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
    }

}
