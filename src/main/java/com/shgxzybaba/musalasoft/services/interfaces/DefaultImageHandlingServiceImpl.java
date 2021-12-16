package com.shgxzybaba.musalasoft.services.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefaultImageHandlingServiceImpl implements ImageHandlingService {
    @Override
    public String processImage(MultipartFile imageFile) {
        return null;
    }
}
