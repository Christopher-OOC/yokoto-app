package com.example.demo.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsServiceUtil {

    private static AWSCredentials setUpAwsCredentials(String accessKey, String secretKey) {

        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        return credentials;
    }

    private static AmazonS3 setUpAmazonS3BucketConnection(String accessKey, String secretKey) {

        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(setUpAwsCredentials(accessKey, secretKey)))
                .withRegion(Regions.US_EAST_2)
                .build();

        return s3;
    }

    public static void uploadFile(String accessKey, String secretKey,
                                  String bucketName, String fileName,
                                  String content) {

        AmazonS3 s3 = setUpAmazonS3BucketConnection(accessKey, secretKey);

        try {

            if (!s3.doesBucketExistV2(bucketName)) {
                s3.createBucket(bucketName);
                s3.putObject(bucketName, fileName, content);
            }
            else {
                s3.putObject(bucketName, fileName, content);
            }
        }
        catch (Exception ex) {
            System.out.println("Uploading of file is not successful!!!");
            ex.printStackTrace();
        }

    }

}
