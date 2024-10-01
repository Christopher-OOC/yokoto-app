package com.example.demo.service;

import com.example.demo.model.entity.MediaPost;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    MediaPost uploadBusinessLogo(String businessId,
                                 MultipartFile multipartFile);

    MediaPost uploadItemImage(String businessId,
                              long itemId,
                              MultipartFile multipartFile);

    byte[] downloadItemImage(String businessId,
                             String mediaUrl);

    MediaPost uploadBusinessImage(String businessId, MultipartFile multipartFile);

}
