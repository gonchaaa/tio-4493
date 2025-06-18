package com.example.appeal_service.controller;

import com.example.appeal_service.DTOs.UserRequestDTO;
import com.example.appeal_service.DTOs.response.UserRequestResponseDTO;
import com.example.appeal_service.services.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-requests")
@RequiredArgsConstructor
public class UserRequestController {
    private final UserRequestService userRequestService;


    @PostMapping
    public ResponseEntity<UserRequestResponseDTO> createUserRequest(@ModelAttribute UserRequestDTO userRequestDTO,
                                                                    @RequestHeader("clientCode") String clientCode) {
        UserRequestResponseDTO response = userRequestService.createUserRequest(userRequestDTO,clientCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequestResponseDTO> getUserRequestById(@PathVariable Long id) {
        UserRequestResponseDTO response = userRequestService.getUserRequestById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserRequestResponseDTO>> getAllUserRequests() {
        List<UserRequestResponseDTO> requests = userRequestService.getAllUserRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserRequestResponseDTO>> getUserRequestsByUserId( @RequestHeader("clientCode") String clientCode){
        List<UserRequestResponseDTO> response = userRequestService.getUserRequestsByUserId(clientCode);
        return ResponseEntity.ok(response);
    }
}
