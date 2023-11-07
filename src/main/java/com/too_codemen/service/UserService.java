package com.too_codemen.service;

import com.too_codemen.entity.User;
import com.too_codemen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User showUserInfo(String email) {
        return userRepository.findByEmail(email);
    }

}
