package com.shgxzybaba.musalasoft.services;

import com.shgxzybaba.musalasoft.services.interfaces.ImageHandlingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefaultImageHandlingServiceImpl implements ImageHandlingService {
    @Override
    public String processImage(MultipartFile imageFile) {
        return null;
    }
}
