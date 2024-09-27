package com.example.demo.service.impl;

import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.MediaPost;
import com.example.demo.service.FileService;
import com.example.demo.utils.AwsServiceUtil;
import com.example.demo.utils.FileEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Value("${my-aws.s3.access-key}")
    private String accessKey;

    @Value("${my-aws.s3.secret-key}")
    private String secretKey;

    @Override
    public MediaPost uploadFile(String businessId,
                                MultipartFile multipartFile) {

        MediaPost mediaPost = new MediaPost();
        System.out.println("Access-Key: " + accessKey);
        System.out.println("Secret-Key: " + secretKey);

        try {

            byte[] fileContent = multipartFile.getBytes();
            String fileToString = FileEncoderUtil.encodedFileToString(fileContent);

            String bucketName = businessId.toLowerCase();
            String fileName = "images/" + new Date().toString();

            AwsServiceUtil.uploadFile(
                    accessKey, secretKey,
                    bucketName, fileName,
                    fileToString);

            mediaPost.setDatePosted(new Date());
            mediaPost.setMediaURL(fileName);

            return mediaPost;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return mediaPost;
    }
}
