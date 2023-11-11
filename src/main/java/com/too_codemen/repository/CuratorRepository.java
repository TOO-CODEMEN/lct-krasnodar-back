package com.too_codemen.repository;

import com.too_codemen.entity.Curator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Long> {
    Optional<Curator> findById(Long id);

    Curator findByEmail(String email);

    Curator save(Curator curator);

    void deleteCuratorById(Long id);

    List<Curator> findAll();

//    Curator findCuratorByUserId(Long id);
}
