package com.too_codemen.controller;

import com.too_codemen.entity.User;
import com.too_codemen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/showUserInfo/{email}")
    public User showUserInfo(@PathVariable String email) {
        return userService.showUserInfo(email);
    }

}
