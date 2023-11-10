package com.too_codemen.controller;

import com.too_codemen.entity.Material;
import com.too_codemen.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/getMaterialById/{id}")
    public Material getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @GetMapping("/getMaterialByName/{name}")
    public Material getMaterialByName(@PathVariable String name) {
        return materialService.getMaterialByName(name);
    }

    @GetMapping("/getAllMaterials")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/getMaterialsByCourseId/{id}")
    public List<Material> getMaterialsByCourseId(@PathVariable Long id) {
        return materialService.getMaterialsByCourseId(id);
    }
}
