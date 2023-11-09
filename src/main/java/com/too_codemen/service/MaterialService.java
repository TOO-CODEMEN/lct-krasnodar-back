package com.too_codemen.service;

import com.too_codemen.entity.Material;
import com.too_codemen.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getMaterialsByCourseId(Long id) {
        return materialRepository.findByCourseId(id);
    }

    public Material getMaterialById(Long id) {
        return materialRepository.findMaterialById(id);
    }

    public Material getMaterialByName(String name) {
        return materialRepository.findMaterialByName(name);
    }

    public Material save(Material material){
        return materialRepository.save(material);
    }

    @Transactional
    public void deleteMaterialById(Long id) {
        materialRepository.deleteMaterialById(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material updateMaterial(Long id, Material updatedMaterial) {
        Material existingMaterial = materialRepository.findById(id).orElse(null);

        if (updatedMaterial.getName() != null) {
            existingMaterial.setName(updatedMaterial.getName());
        }
        if (updatedMaterial.getDescription() != null) {
            existingMaterial.setDescription(updatedMaterial.getDescription());
        }
        if (updatedMaterial.getAudience() != null) {
            existingMaterial.setAudience(updatedMaterial.getAudience());
        }
        if (updatedMaterial.getYandexFormsLink() != null) {
            existingMaterial.setYandexFormsLink(updatedMaterial.getYandexFormsLink());
        }
        if (updatedMaterial.getLink() != null) {
            existingMaterial.setLink(updatedMaterial.getLink());
        }
        if (updatedMaterial.getCourse() != null) {
            existingMaterial.setCourse(updatedMaterial.getCourse());
        }
        return materialRepository.save(existingMaterial);
    }

}
