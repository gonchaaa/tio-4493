package com.example.appeal_service.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppealPurposeDTO {
    private String name;
    private Long categoryId;
}
