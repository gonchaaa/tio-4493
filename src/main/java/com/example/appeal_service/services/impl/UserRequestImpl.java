package com.example.appeal_service.services.impl;

import com.example.appeal_service.DTOs.UserRequestDTO;
import com.example.appeal_service.DTOs.feign.AccountDTO;
import com.example.appeal_service.DTOs.response.UserRequestResponseDTO;
import com.example.appeal_service.entities.AppealCategory;
import com.example.appeal_service.entities.AppealPurpose;
import com.example.appeal_service.entities.UserRequest;
import com.example.appeal_service.enums.Status;
import com.example.appeal_service.feign.DemoClient;
import com.example.appeal_service.repositories.AppealCategoryRepository;
import com.example.appeal_service.repositories.AppealPurposeRepository;
import com.example.appeal_service.repositories.UserRequestRepository;
import com.example.appeal_service.services.FileStorageService;
import com.example.appeal_service.services.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRequestImpl implements UserRequestService {
    private final UserRequestRepository userRequestRepository;
    private final AppealCategoryRepository appealCategoryRepository;
    private final AppealPurposeRepository appealPurposeRepository;
    private final FileStorageService fileStorageService;
    private final DemoClient demoClient;

    @Override
    public UserRequestResponseDTO createUserRequest(UserRequestDTO userRequestDTO) {
        AppealCategory appealCategory = appealCategoryRepository.findById(userRequestDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found with id: " + userRequestDTO.getCategoryId()));
        AppealPurpose appealPurpose = appealPurposeRepository.findById(userRequestDTO.getPurposeId())
                .orElseThrow(()-> new RuntimeException("Purpose not found with id: " + userRequestDTO.getPurposeId()));

        String voicePath = fileStorageService.saveFile(userRequestDTO.getVoiceMessage(),"voices");
        String filePath = fileStorageService.saveFile(userRequestDTO.getFile(),"files");

        UserRequest userRequest = new UserRequest();
        userRequest.setDescription(userRequestDTO.getDescription());
        userRequest.setCategory(appealCategory);
        userRequest.setPurpose(appealPurpose);
        userRequest.setVoiceMessage(voicePath);
        userRequest.setAttachmentPath(filePath);
        userRequest.setStatus(Status.IN_PROGRESS);
        userRequest.setDate(LocalDate.now());

        if(appealCategory.getId()==3){
            List<AccountDTO> cards = demoClient.getAccountByUserId(userRequestDTO.getUserId());
            if(cards.isEmpty()) {
                throw new RuntimeException("No cards found for user with id: " + userRequestDTO.getUserId());
            }

        }

        userRequest= userRequestRepository.save(userRequest);

        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        UserRequestResponseDTO userRequestResponseDTO = new UserRequestResponseDTO();
        userRequestResponseDTO.setDescription(userRequest.getDescription());
        userRequestResponseDTO.setCategoryId(userRequest.getCategory().getId());
        userRequestResponseDTO.setPurposeId(userRequest.getPurpose().getId());
        userRequestResponseDTO.setStatus(userRequest.getStatus());
        userRequestResponseDTO.setDate(userRequest.getDate());
        userRequestResponseDTO.setVoiceMessage(baseUrl + "/api/files/download?path=" + userRequest.getVoiceMessage());
        userRequestResponseDTO.setFile(baseUrl + "/api/files/download?path=" + userRequest.getAttachmentPath());

        return userRequestResponseDTO;
    }

    @Override
    public UserRequestResponseDTO getUserRequestById(Long id) {
        return null;
    }

    @Override
    public List<UserRequestResponseDTO> getAllUserRequests() {
        return List.of();
    }
}
