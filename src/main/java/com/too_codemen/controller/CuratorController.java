package com.too_codemen.controller;

import com.too_codemen.entity.Curator;
import com.too_codemen.service.CuratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/curators")
public class CuratorController {

    @Autowired
    private CuratorService curatorService;

    @GetMapping("/getCuratorByUserId/{id}")
    public Curator getCuratorByUserId(@PathVariable Long id) {
        return curatorService.getCuratorByUserId(id);
    }
}
