package com.example.appeal_service.controller;

import com.example.appeal_service.DTOs.AppealPurposeDTO;
import com.example.appeal_service.services.AppealPurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class AppealPurposeController {

    private final AppealPurposeService appealPurposeService;

    @PostMapping()
    public AppealPurposeDTO createAppealPurpose(@RequestBody AppealPurposeDTO appealPurposeDTO) {
        return appealPurposeService.createAppealPurpose(appealPurposeDTO);
    }

}
