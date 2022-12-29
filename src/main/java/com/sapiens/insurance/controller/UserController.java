package com.sapiens.insurance.controller;

import com.sapiens.insurance.entity.User;
import com.sapiens.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser/PM")
    public ResponseEntity register(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
