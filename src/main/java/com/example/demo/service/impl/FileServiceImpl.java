package com.example.demo.service.impl;

import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.MediaPost;
import com.example.demo.service.FileService;
import com.example.demo.utils.AwsServiceUtil;
import com.example.demo.utils.FileEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public MediaPost uploadFile(BusinessRetailDto businessRetailDto,
                                MultipartFile multipartFile) {

        try {

            byte[] fileContent = multipartFile.getBytes();
            String fileToString = FileEncoderUtil.encodedFileToString(fileContent);

            AwsServiceUtil.uploadFile();




        }
        catch (IOException ex) {

        }



    }
}
