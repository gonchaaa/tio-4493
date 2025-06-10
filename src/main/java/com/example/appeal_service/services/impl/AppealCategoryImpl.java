package com.example.appeal_service.services.impl;

import com.example.appeal_service.DTOs.AppealCategoryDTO;
import com.example.appeal_service.entities.AppealCategory;
import com.example.appeal_service.repositories.AppealCategoryRepository;
import com.example.appeal_service.services.AppealCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppealCategoryImpl implements AppealCategoryService {

    private final AppealCategoryRepository appealCategoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public AppealCategoryDTO createCategory(AppealCategoryDTO appealCategoryDTO) {

        AppealCategoryDTO savedCategory = new AppealCategoryDTO();
        AppealCategory appealCategory = new AppealCategory();
        modelMapper.map(appealCategoryDTO, appealCategory);
        AppealCategory savedAppealCategory = appealCategoryRepository.save(appealCategory);
        modelMapper.map(savedAppealCategory, savedCategory);
        return savedCategory;
    }

    @Override
    public AppealCategoryDTO getCategoryById(Long id) {

        Optional<AppealCategory> optionalCategory = appealCategoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            AppealCategory appealCategory = optionalCategory.get();
            AppealCategoryDTO appealCategoryDTO = new AppealCategoryDTO();
            modelMapper.map(appealCategory, appealCategoryDTO);
            return appealCategoryDTO;
        }
        throw new RuntimeException("Category not found with id: " + id);
    }

    @Override
    public List<AppealCategoryDTO> getAllCategories() {

        List<AppealCategoryDTO> appealCategoryDTOList =new ArrayList<>();
        List<AppealCategory> categories = appealCategoryRepository.findAll();

        for (AppealCategory category : categories) {
            AppealCategoryDTO appealCategoryDTO = new AppealCategoryDTO();
            modelMapper.map(category, appealCategoryDTO);
            appealCategoryDTOList.add(appealCategoryDTO);
        }
        return appealCategoryDTOList;
    }
}
