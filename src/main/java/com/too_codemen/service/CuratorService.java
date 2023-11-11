package com.too_codemen.service;

import com.too_codemen.entity.Curator;
import com.too_codemen.entity.User;
import com.too_codemen.model.CuratorRequest;
import com.too_codemen.repository.CuratorRepository;
import com.too_codemen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CuratorService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CuratorRepository curatorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public Curator getCuratorByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
//        System.out.println(user);
        Long curatorId = user.getCurator().getId();
        Curator curator = curatorRepository.findById(curatorId).orElse(null);
        curator.getUsers().size();
        return curator;
    }

    @Transactional
    public Curator saveCurator(Curator curatorRequest) {
        emailService.sendNotification(curatorRequest.getEmail(), "Ваши данные для входа", "Ваш логин: "
                + curatorRequest.getEmail() + " Ваш пароль: " + curatorRequest.getPassword());

        String encodedPassword = passwordEncoder.encode(curatorRequest.getPassword());
        curatorRequest.setPassword(encodedPassword);
        return curatorRepository.save(curatorRequest);
    }
    @Transactional
    public void deleteCuratorById(Long id) {
//        User user = userRepository.findUserByCuratorId(id);
//        user.setCurator(null);
//        if (user != null) {
//            userService.updateUser(user.getId(), user);
//        }


        curatorRepository.deleteCuratorById(id);
    }

    public List<Curator> getAllCurators() {
        return curatorRepository.findAll();
    }

    public Curator showCuratorInfo(String email) {
        return curatorRepository.findByEmail(email);
    }

    public Curator updateCurator(Long id, Curator updatedCurator) {
        Curator existingCurator = curatorRepository.findById(id).orElse(null);
        if (updatedCurator.getName() != null) {
            existingCurator.setName(updatedCurator.getName());
        }
        if (updatedCurator.getSurname() != null) {
            existingCurator.setSurname(updatedCurator.getSurname());
        }
        if (updatedCurator.getPatronymic() != null) {
            existingCurator.setPatronymic(updatedCurator.getPatronymic());
        }
        if (updatedCurator.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(updatedCurator.getPassword());
            updatedCurator.setPassword(encodedPassword);
            existingCurator.setPassword(updatedCurator.getPassword());
        }
        if (updatedCurator.getPosition() != null) {
            existingCurator.setPosition(updatedCurator.getPosition());
        }
        if (updatedCurator.getEmail() != null) {
            existingCurator.setEmail(updatedCurator.getEmail());
        }
        if (updatedCurator.getNumber() != null) {
            existingCurator.setNumber(updatedCurator.getNumber());
        }
        if (updatedCurator.getTelegram() != null) {
            existingCurator.setTelegram(updatedCurator.getTelegram());
        }
        if (updatedCurator.getRole() != null) {
            existingCurator.setRole(updatedCurator.getRole());
        }

        return curatorRepository.save(existingCurator);
    }

    public List<User> getUsersByCuratorId(Long id) {
        return userRepository.findUsersByCuratorId(id);
    }
}
