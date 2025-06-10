package com.example.appeal_service.controller;

import com.example.appeal_service.DTOs.AppealCategoryDTO;
import com.example.appeal_service.repositories.AppealCategoryRepository;
import com.example.appeal_service.services.AppealCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class AppealCategoryController {

    private final AppealCategoryService appealCategoryService;

    @PostMapping()
    public AppealCategoryDTO createCategory(@RequestBody AppealCategoryDTO appealCategoryDTO) {
        return appealCategoryService.createCategory(appealCategoryDTO);
    }

    @GetMapping("/{id}")
    public AppealCategoryDTO getCategoryById(@PathVariable(name = "id") Long id) {
        return appealCategoryService.getCategoryById(id);
    }
    @GetMapping()
    public List<AppealCategoryDTO> getAllCategories() {
        return appealCategoryService.getAllCategories();
    }
}
