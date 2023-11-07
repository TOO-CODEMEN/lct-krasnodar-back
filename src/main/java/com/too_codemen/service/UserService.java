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

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getSurname() != null) {
            existingUser.setSurname(updatedUser.getSurname());
        }
        if (updatedUser.getPatronymic() != null) {
            existingUser.setPatronymic(updatedUser.getPatronymic());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getPosition() != null) {
            existingUser.setPosition(updatedUser.getPosition());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getNumber() != null) {
            existingUser.setNumber(updatedUser.getNumber());
        }
        if (updatedUser.getTelegram() != null) {
            existingUser.setTelegram(updatedUser.getTelegram());
        }
        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }
        if (updatedUser.getPrimaryOnboarding() != null) {
            existingUser.setPrimaryOnboarding(updatedUser.getPrimaryOnboarding());
        }

        return userRepository.save(existingUser);
    }

}
