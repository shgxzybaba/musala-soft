package com.shgxzybaba.musalasoft.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageHandlingService {
    String processImage(MultipartFile imageFile);
}
