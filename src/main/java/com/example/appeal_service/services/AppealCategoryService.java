package com.example.appeal_service.services;

import com.example.appeal_service.DTOs.AppealCategoryDTO;

import java.util.List;

public interface AppealCategoryService {
    AppealCategoryDTO createCategory(AppealCategoryDTO appealCategoryDTO);
    AppealCategoryDTO getCategoryById(Long id);
    List<AppealCategoryDTO> getAllCategories();

}
