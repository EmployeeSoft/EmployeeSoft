package com.example.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Configuration
@PropertySource("classpath:application.properties")
public class AWSConfig {
    @Value("${amazonaws.bucketName}")
    private String bucketName;

    @Value("${amazonaws.accessKey}")
    private String accessKey;

    @Value("${amazonaws.secretKey}")
    private String secretKey;

    @Value("${amazonaws.region}")
    private String region;

    AmazonS3 s3client;
    TransferManager tm;

    @PostConstruct
    private void init() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();

        tm = TransferManagerBuilder
                .standard()
                .withS3Client(s3client)
                .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
                .build();
    }

    public String uploadDocument(MultipartFile file, String filename) {
        File convertFile = new File(filename);

        try {
            FileOutputStream outputStream = new FileOutputStream(convertFile);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.s3client.putObject(
                new PutObjectRequest(bucketName, filename, convertFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return getURL(filename);
    }

    public void getDocument(String filename) {
        s3client.getObject(this.bucketName, filename);
    }

    public String getURL(String key) {
        return "https://" + this.bucketName + ".s3-" + this.region + ".amazonaws.com/" + key;
    }

    public String getBucketName() { return this.bucketName; }
}
