package com.too_codemen.repository;

import com.too_codemen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    User findByEmail(String email);

    User save(User user);

    void deleteUserById(Long id);

    List<User> findAll();

}
