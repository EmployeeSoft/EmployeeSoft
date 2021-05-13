package com.example.demo.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@PropertySource("classpath:application.properties")
public class AWSS3Service {
    @Value("${amazonaws.bucketName}")
    private String bucketName;

    @Value("${amazonaws.accessKey}")
    private String accessKey;

    @Value("${amazonaws.secretKey}")
    private String secretKey;

    @Value("${amazonaws.region}")
    private String region;

    private AmazonS3 s3client;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PersonService personService;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public boolean uploadFile(MultipartFile multipartFile, Integer userId) {
        String filename = "";
        try {
            File file = convertMultipartFileToFile(multipartFile);
            filename = multipartFile.getOriginalFilename();
            uploadFileToBucket(filename, file);

            // Update the avatar of the employee with the user ID of userId
            int personId = personService.getPersonIdByUserId(userId);
            String avatarUrl = getURL(filename);
            employeeService.updateEmployeeAvatarByPersonId(personId, avatarUrl);
            file.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private void uploadFileToBucket(String fileName, File file) {
        this.s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String getURL(String key) {
        return "https://" + this.bucketName + ".s3-" + this.region + ".amazonaws.com/" + key;
    }
}
