package com.too_codemen.service;

import com.too_codemen.CustomUserDetails;
import com.too_codemen.entity.Curator;
import com.too_codemen.entity.Role;
import com.too_codemen.entity.User;
import com.too_codemen.repository.CuratorRepository;
import com.too_codemen.repository.TaskRepository;
import com.too_codemen.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
    private CuratorRepository curatorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;
    @Autowired
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Curator curator = curatorRepository.findByEmail(email);

        if (curator == null) {
            User user = userRepository.findByEmail(email);
            return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRole());
        }

        return new CustomUserDetails(curator.getEmail(), curator.getPassword(), curator.getRole());
    }


    public User saveUser(User user) {
        Curator curator = curatorRepository.findById(user.getCurator().getId()).orElse(null);
        emailService.sendNotification(user.getEmail(), "Ваши данные для входа", "Ваш логин: "
                + user.getEmail() + " Ваш пароль: " + user.getPassword() + " Ваш куратор: " + curator.getSurname() +
                " " + curator.getName() + " " + curator.getPatronymic() + " Почта для связи: " + curator.getEmail());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        taskRepository.deleteTasksByUserId(userId);
        // Получаем пользователя из базы данных
        User user = userRepository.findById(userId).orElse(null);

        // Проверяем, что пользователь существует
        if (user != null) {
            // Отвязываем пользователя от куратора
            user.setCurator(null);

            // Сохраняем изменения (это может быть merge в вашем случае)
            userRepository.save(user);

            // Удаляем пользователя из базы данных
            userRepository.delete(user);
        }
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
        if (updatedUser.getStartTime() != null) {
            existingUser.setStartTime(updatedUser.getStartTime());
        }
        if (updatedUser.getFinishTime() != null) {
            existingUser.setFinishTime(updatedUser.getFinishTime());
        }
        if (updatedUser.getCompletedTasks() != null) {
            existingUser.setCompletedTasks(updatedUser.getCompletedTasks());
        }
        if (updatedUser.getFailedTasks() != null) {
            existingUser.setFailedTasks(updatedUser.getFailedTasks());
        }

        return userRepository.save(existingUser);
    }

}
