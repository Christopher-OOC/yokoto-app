package com.example.demo.service.impl;

import com.example.demo.exception.FileUploadingException;
import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.entity.MediaPost;
import com.example.demo.repository.MediaPostRepository;
import com.example.demo.service.FileService;
import com.example.demo.utils.AwsServiceUtil;
import com.example.demo.utils.FileEncoderUtil;
import com.example.demo.utils.PublicIdGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MediaPostRepository mediaPostRepository;

    @Value("${my-aws.s3.access-key}")
    private String accessKey;

    @Value("${my-aws.s3.secret-key}")
    private String secretKey;

    @Override
    public MediaPost uploadBusinessLogo(String businessId, MultipartFile multipartFile) {

        String fileName = "images/" +
                "business-logo/" + PublicIdGeneratorUtils.generatePublicId(20);

        MediaPost mediaPost = uploadFile(businessId, fileName, multipartFile);

        return mediaPost;
    }

    public MediaPost uploadItemImage(String businessId,
                                     long itemId,
                                     MultipartFile multipartFile) {

        String fileName = "images/" + "items/" +
                itemId + "/" + PublicIdGeneratorUtils.generatePublicId(20);

        MediaPost mediaPost = uploadFile(businessId, fileName, multipartFile);

        return mediaPost;
    }

    @Override
    public byte[] downloadItemImage(String businessId,
                                    String mediaUrl) {

        return downloadFile(businessId, mediaUrl);
    }

    private MediaPost uploadFile(String businessId,
                                 String fileName,
                                 MultipartFile multipartFile) {

        MediaPost mediaPost = new MediaPost();
        mediaPost.setFileType(multipartFile.getContentType());

        try {

            byte[] fileContent = multipartFile.getBytes();
            String fileToString = FileEncoderUtil.encodedFileToString(fileContent);

            String bucketName = businessId.toLowerCase();

//            AwsServiceUtil.uploadFile(
//                    accessKey, secretKey,
//                    bucketName, fileName,
//                    fileToString);

            mediaPost.setDatePosted(new Date());
            mediaPost.setMediaUrl(fileName);
        }
        catch (IOException ex) {
            throw new FileUploadingException();
        }

        return mediaPostRepository.save(mediaPost);
    }

    private byte[] downloadFile(String businessId, String mediaUrl) {

        byte[] fileContent;

        try {

            String bucketName = businessId.toLowerCase();

            fileContent = AwsServiceUtil.downloadFile(
                    accessKey, secretKey,
                    bucketName, mediaUrl);

        }
        catch (Exception ex) {
            throw new NoResourceFoundException("We couldn't get such file!!!");
        }

        return fileContent;
    }

}
