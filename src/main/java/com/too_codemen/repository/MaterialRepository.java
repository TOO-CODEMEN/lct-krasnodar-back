package com.too_codemen.repository;

import com.too_codemen.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Material findMaterialById(Long id);

    Material findMaterialByName(String name);

    Material save(Material material);

    Material deleteMaterialById(Long id);

    List<Material> findAll();

}
