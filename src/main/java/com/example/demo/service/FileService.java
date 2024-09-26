package com.example.demo.service;

import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.MediaPost;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    MediaPost uploadFile(BusinessRetailDto businessRetailDto, MultipartFile multipartFile);

}
