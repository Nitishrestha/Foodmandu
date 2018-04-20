package com.foodorderingapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file, String fileName);

    MultipartFile getEmptyImage()throws Exception;
}

