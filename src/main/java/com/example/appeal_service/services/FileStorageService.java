package com.example.appeal_service.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveFile(MultipartFile file, String folderName);
}
