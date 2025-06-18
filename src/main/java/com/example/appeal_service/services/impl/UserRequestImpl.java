package com.example.appeal_service.services.impl;

import com.example.appeal_service.DTOs.UserRequestDTO;
import com.example.appeal_service.DTOs.feign.AccountDTO;
import com.example.appeal_service.DTOs.response.UserRequestResponseDTO;
import com.example.appeal_service.entities.AppealCategory;
import com.example.appeal_service.entities.AppealPurpose;
import com.example.appeal_service.entities.UserRequest;
import com.example.appeal_service.enums.Status;
import com.example.appeal_service.repositories.AppealCategoryRepository;
import com.example.appeal_service.repositories.AppealPurposeRepository;
import com.example.appeal_service.repositories.UserRequestRepository;
import com.example.appeal_service.services.FileStorageService;
import com.example.appeal_service.services.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRequestImpl implements UserRequestService {
    private final UserRequestRepository userRequestRepository;
    private final AppealCategoryRepository appealCategoryRepository;
    private final AppealPurposeRepository appealPurposeRepository;
    private final FileStorageService fileStorageService;
    // private final DemoClient demoClient; feign ucun istifade olunan
    private final CardListForClientCode cardListForClientCode;
    private final ModelMapper modelMapper;

        @Override
        public UserRequestResponseDTO createUserRequest(UserRequestDTO userRequestDTO,String clientCode) {
            AppealCategory appealCategory = appealCategoryRepository.findById(userRequestDTO.getCategoryId())
                    .orElseThrow(()-> new RuntimeException("Category not found with id: " + userRequestDTO.getCategoryId()));
            AppealPurpose appealPurpose = appealPurposeRepository.findById(userRequestDTO.getPurposeId())
                    .orElseThrow(()-> new RuntimeException("Purpose not found with id: " + userRequestDTO.getPurposeId()));

            String voicePath = fileStorageService.saveFile(userRequestDTO.getVoiceMessage(),"voices");
            String filePath = fileStorageService.saveFile(userRequestDTO.getFile(),"files");

            if(appealCategory.getId()==3){
                List<AccountDTO> cards=cardListForClientCode.getCardsByClientCode(clientCode);
                if (cards.isEmpty()){
                    throw new RuntimeException("Istifadəçinin kartı yoxdur.");
                }
            }

            Long selectedCardId = userRequestDTO.getCardId();

            UserRequest userRequest = new UserRequest();
            userRequest.setDescription(userRequestDTO.getDescription());
            userRequest.setCategory(appealCategory);
            userRequest.setPurpose(appealPurpose);
            userRequest.setVoiceMessage(voicePath);
            userRequest.setAttachmentPath(filePath);
            userRequest.setStatus(Status.IN_PROGRESS);
            userRequest.setCreatedDate(LocalDateTime.now());
            userRequest.setClientCode(clientCode);
            userRequest.setCardId(selectedCardId);

            userRequest= userRequestRepository.save(userRequest);



            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

            UserRequestResponseDTO userRequestResponseDTO = new UserRequestResponseDTO();
            userRequestResponseDTO.setDescription(userRequest.getDescription());
            userRequestResponseDTO.setCategoryId(userRequest.getCategory().getId());
            userRequestResponseDTO.setPurposeId(userRequest.getPurpose().getId());
            userRequestResponseDTO.setStatus(userRequest.getStatus());
            userRequestResponseDTO.setCreatedDate(userRequest.getCreatedDate());
            userRequestResponseDTO.setCardId(userRequest.getCardId());
            userRequestResponseDTO.setVoiceMessage(baseUrl + "/api/files/download?path=" + userRequest.getVoiceMessage());
            userRequestResponseDTO.setFile(baseUrl + "/api/files/download?path=" + userRequest.getAttachmentPath());

            return userRequestResponseDTO;
        }

    @Override
    public UserRequestResponseDTO getUserRequestById(Long id) {

        Optional<UserRequest> optionalUserRequest = userRequestRepository.findById(id);
        if (optionalUserRequest.isPresent()) {
            UserRequest userRequest = optionalUserRequest.get();
            UserRequestResponseDTO userRequestResponseDTO = new UserRequestResponseDTO();
            userRequestResponseDTO.setDescription(userRequest.getDescription());
            userRequestResponseDTO.setCategoryId(userRequest.getCategory().getId());
            userRequestResponseDTO.setPurposeId(userRequest.getPurpose().getId());
            userRequestResponseDTO.setStatus(userRequest.getStatus());
            userRequestResponseDTO.setCreatedDate(userRequest.getCreatedDate());
            userRequestResponseDTO.setCardId(userRequest.getCardId());
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            userRequestResponseDTO.setVoiceMessage(baseUrl + "/api/files/download?path=" + userRequest.getVoiceMessage());
            userRequestResponseDTO.setFile(baseUrl + "/api/files/download?path=" + userRequest.getAttachmentPath());
            return userRequestResponseDTO;
        }

        return null;
    }

    @Override
    public List<UserRequestResponseDTO> getAllUserRequests() {
        List<UserRequest> userRequests = userRequestRepository.findAll();
        return userRequests.stream()
                .map(userRequest -> modelMapper.map(userRequest, UserRequestResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRequestResponseDTO> getUserRequestsByUserId(String clientCode) {
    List<UserRequest> userRequests = userRequestRepository.findByClientCode(clientCode);
    if (userRequests != null && !userRequests.isEmpty()) {
        return userRequests.stream()
                .sorted(Comparator.comparing(UserRequest::getCreatedDate).reversed())
                .map(userRequest -> modelMapper.map(userRequest, UserRequestResponseDTO.class))
                .collect(Collectors.toList());
    }
    throw new RuntimeException("No user requests found for user with id: " + clientCode);
    }
}
