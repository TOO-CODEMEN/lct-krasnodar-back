package com.too_codemen.service;

import com.too_codemen.entity.User;
import com.too_codemen.repository.TaskRepository;
import com.too_codemen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        taskRepository.deleteTasksByUserId(id);
        userRepository.deleteUserById(id);
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
            String encodedPassword = passwordEncoder.encode(updatedUser.getPassword());
            updatedUser.setPassword(encodedPassword);
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
