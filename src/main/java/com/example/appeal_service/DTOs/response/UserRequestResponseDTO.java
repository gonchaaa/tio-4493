package com.example.appeal_service.DTOs.response;

import com.example.appeal_service.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestResponseDTO {
        private String description;
        private String voiceMessage;
        private String file;
        private Long categoryId;
        private Long purposeId;
        private LocalDate date;
        private Status status;
}
