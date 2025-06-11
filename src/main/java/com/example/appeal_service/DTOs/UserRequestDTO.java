package com.example.appeal_service.DTOs;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @Size(min = 1, max = 600)
    private String description;
    private MultipartFile voiceMessage;
    private MultipartFile file;
    private Long categoryId;
    private Long purposeId;
    private Long userId;
}
