package ru.dnina.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dnina.server.services.SignOutService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class SignOutController {

    @Autowired
    @Qualifier("signOutServiceImpl")
    private SignOutService signOutService;

    @GetMapping("/signOut")
    public void signOut(HttpServletRequest request, HttpServletResponse response)  {
        signOutService.signOut();

    }
}
