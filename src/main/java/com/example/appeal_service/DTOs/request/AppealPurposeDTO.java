package com.example.appeal_service.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppealPurposeDTO {
    private Long id;
    private String name;
    private Long categoryId;
}
