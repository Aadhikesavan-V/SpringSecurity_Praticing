package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Users;
import com.example.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/admin/add")
    public void addUser(@RequestBody Users user){
        userService.addUser(user);
    }
}
