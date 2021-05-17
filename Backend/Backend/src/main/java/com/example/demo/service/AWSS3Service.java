package com.example.demo.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.example.demo.domain.PersonalDocumentDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;

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

    @Autowired
    private PersonalDocService personalDocService;

    @Autowired
    private DigitalDocumentService digitalDocumentService;

    @Autowired
    private AppWorkFlowService appWorkFlowService;

    @PostConstruct
    private void init() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String getURL(String key) {
        return "https://" + this.bucketName + ".s3-" + this.region + ".amazonaws.com/" + key;
    }

    public boolean uploadFile(MultipartFile mFile, String uploadTo, Integer userId, String fileTitle) {
        /**
         * mFile = File type - the file the user wants to upload
         * uploadTo = String - can either be "avatar" or "personal document"
         * userId = Integer - the user's ID
         * fileTitle = String - Can be either "OPT Receipt", "OPT STEM Receipt", "OPT EAD", "I-983", "I-983 Signed",
         *                      "I-20", or "OPT STEM EAD"
         **/

        String filename = "";
        try {
            File file = new File(mFile.getOriginalFilename());
            FileOutputStream out = new FileOutputStream(file);
            out.write(mFile.getBytes());

            filename = userId + "_" + mFile.getOriginalFilename();
            String fileUrl = getURL(filename);

            // Upload the document/file
            this.s3client.putObject(new PutObjectRequest(bucketName, filename, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));    // Make file public

            // Update employee with the user ID of {userId}
            int personId = personService.getPersonIdByUserId(userId);

            // If {uploadTo} = avatar
            if (uploadTo.equals("avatar")) {
                employeeService.updateEmployeeAvatarByPersonId(personId, fileUrl);
                System.out.println("User ID " + userId + " successfully updated profile avatar");
            } else {
                int employeeId = employeeService.getEmployeeIdByPersonId(personId);
                PersonalDocumentDomain create = personalDocService.createPersonalDocumentByEmployeeId(
                        employeeId, fileUrl, filename, fileTitle);

                // if the file is successfully added to the database
                if (create != null) {
                    if (fileTitle.equals("OPT Receipt")) {
                        // If the filename is OPT Receipt then create a row in the app_workflow table
                        appWorkFlowService.createAppWorkFlow(userId, fileTitle);
                        System.out.println("Added OPT Receipt to employee ID " + employeeId);
                    } else {
                        // If the filename is not OPT Receipt than update the row in the app_workflow table
                        // where the user ID is {userId} and the filename is {filename}
                        appWorkFlowService.updateCurrentAppWorkFlow(userId);    // Set the current in app_workflow to false
                        appWorkFlowService.createAppWorkFlow(userId, fileTitle);

                        System.out.println("Added " + fileTitle + " to employee ID " + employeeId);
                    }

                    System.out.println("Employee ID " + employeeId + " successfully uploaded document " + fileTitle);
                }
            }

            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] downloadFile(Integer userId, String filename) {
        byte[] data = null;

        /*
            if userId is 0 then
                filename can either be "I-983 Form" or "I-983 Sample"
            else
                filename is the file title that is in the personal_doc table in backend database
        */

        String key;

        // If userId is 0 then download from digital_doc
        if (userId == 0) {
            key = digitalDocumentService.getDigitalDocument(filename)
                    .replace("https://employeefilebucket.s3-us-west-1.amazonaws.com/", "")
                    .replace("+", " ");
        } else {
            key = personalDocService.getPath(userId, filename)
                    .replace("https://employeefilebucket.s3-us-west-1.amazonaws.com/", "");
        }

        S3Object s3Object = s3client.getObject(bucketName, key);
        S3ObjectInputStream in = s3Object.getObjectContent();

        try {
            data = IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // If the stream still contain data, abort HTTP request
                s3Object.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }
}
