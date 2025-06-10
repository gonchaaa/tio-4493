package com.example.appeal_service.services;

import com.example.appeal_service.DTOs.AppealPurposeDTO;

import java.util.List;

public interface AppealPurposeService {
    AppealPurposeDTO createAppealPurpose(AppealPurposeDTO appealPurposeDTO);
    AppealPurposeDTO getAppealPurposeById(Long id);
    List<AppealPurposeDTO> getAllAppealPurposes();
}
