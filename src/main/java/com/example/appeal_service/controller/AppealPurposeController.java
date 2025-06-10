package com.example.appeal_service.controller;

import com.example.appeal_service.DTOs.AppealPurposeDTO;
import com.example.appeal_service.services.AppealPurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class AppealPurposeController {

    private final AppealPurposeService appealPurposeService;

    @PostMapping()
    public AppealPurposeDTO createAppealPurpose(@RequestBody AppealPurposeDTO appealPurposeDTO) {
        return appealPurposeService.createAppealPurpose(appealPurposeDTO);
    }
    @GetMapping("/{id}")
    public AppealPurposeDTO getAppealPurposeById(@PathVariable(name = "id") Long id) {
        return appealPurposeService.getAppealPurposeById(id);
    }
    @GetMapping()
    public List<AppealPurposeDTO> getAppealAll(){
        return appealPurposeService.getAllAppealPurposes();
    }

}
