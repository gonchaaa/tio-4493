package com.example.appeal_service.DTOs;

import com.example.appeal_service.entities.AppealCategory;
import com.example.appeal_service.entities.AppealPurpose;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

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
}
