package com.example.appeal_service.services;

import com.example.appeal_service.DTOs.UserRequestDTO;
import com.example.appeal_service.DTOs.response.UserRequestResponseDTO;

import java.util.List;

public interface UserRequestService {
    UserRequestResponseDTO createUserRequest(UserRequestDTO userRequestDTO,String clientCode);
    UserRequestResponseDTO getUserRequestById(Long id);
    List<UserRequestResponseDTO> getAllUserRequests();
    List<UserRequestResponseDTO> getUserRequestsByUserId(Long userId);
}
