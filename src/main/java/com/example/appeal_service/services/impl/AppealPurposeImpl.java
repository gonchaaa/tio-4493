package com.example.appeal_service.services.impl;

import com.example.appeal_service.DTOs.AppealPurposeDTO;
import com.example.appeal_service.entities.AppealCategory;
import com.example.appeal_service.entities.AppealPurpose;
import com.example.appeal_service.repositories.AppealCategoryRepository;
import com.example.appeal_service.repositories.AppealPurposeRepository;
import com.example.appeal_service.services.AppealPurposeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppealPurposeImpl implements AppealPurposeService {

    private final AppealPurposeRepository appealPurposeRepository;
    private final AppealCategoryRepository appealCategoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public AppealPurposeDTO createAppealPurpose(AppealPurposeDTO appealPurposeDTO) {
    AppealCategory appealCategory = appealCategoryRepository.findById(appealPurposeDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Appeal category not found with id: " + appealPurposeDTO.getCategoryId()));

        AppealPurpose appealPurpose = new AppealPurpose();
        modelMapper.map(appealPurposeDTO, appealPurpose);
        appealPurpose.setCategory(appealCategory);

        AppealPurpose savedAppealPurpose = appealPurposeRepository.save(appealPurpose);
        AppealPurposeDTO savedAppealPurposeDTO = new AppealPurposeDTO();
        modelMapper.map(savedAppealPurpose, savedAppealPurposeDTO);

        return savedAppealPurposeDTO;
    }


    @Override
    public AppealPurposeDTO getAppealPurposeById(Long id) {

        Optional<AppealPurpose> appealPurposeOptional = appealPurposeRepository.findById(id);
        if (appealPurposeOptional.isPresent()) {
            AppealPurpose appealPurpose = appealPurposeOptional.get();
            AppealPurposeDTO appealPurposeDTO = new AppealPurposeDTO();
            modelMapper.map(appealPurpose, appealPurposeDTO);
            return appealPurposeDTO;
        }

        throw new RuntimeException("Appeal purpose not found with id: " + id);
    }

    @Override
    public List<AppealPurposeDTO> getAllAppealPurposes() {

        List<AppealPurpose> appealPurposes = appealPurposeRepository.findAll();
        List<AppealPurposeDTO> appealPurposeDTOs = appealPurposes.stream()
                .map(appealPurpose -> {
                    AppealPurposeDTO appealPurposeDTO = new AppealPurposeDTO();
                    modelMapper.map(appealPurpose, appealPurposeDTO);
                    return appealPurposeDTO;
                })
                .toList();


        return appealPurposeDTOs;
    }
}
