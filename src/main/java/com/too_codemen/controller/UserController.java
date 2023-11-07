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

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/showUserInfo/{email}")
    public User showUserInfo(@PathVariable String email) {
        return userService.showUserInfo(email);
    }

}
