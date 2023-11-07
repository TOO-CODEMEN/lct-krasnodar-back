package com.too_codemen.service;

import com.too_codemen.entity.Material;
import com.too_codemen.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public Material getMaterialById(Long id) {
        return materialRepository.findMaterialById(id);
    }

    public Material getMaterialByName(String name) {
        return materialRepository.findMaterialByName(name);
    }

    public Material save(Material material){
        return materialRepository.save(material);
    }

    public Material deleteMaterialById(Long id) {
        return materialRepository.deleteMaterialById(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
