package com.demo.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.journalApp.entity.User;
import com.demo.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){return "Ok";}


    
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }
}
