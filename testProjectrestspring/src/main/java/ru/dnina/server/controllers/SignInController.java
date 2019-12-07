package ru.dnina.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dnina.server.forms.SignInFormImpl;
import ru.dnina.server.services.SignInService;
import ru.dnina.server.transfer.TokenDto;

@RestController
@RequestMapping("/api")
public class SignInController {

    @Autowired
    @Qualifier("signInServiceImpl")
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInFormImpl form){
       try {
           return ResponseEntity.ok(signInService.signIn(form));
       }
       catch (IllegalArgumentException exp){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }
}
