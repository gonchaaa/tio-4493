package com.example.appeal_service.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppealPurposeResponseDTO {
    private Long id;
    private String name;
}
