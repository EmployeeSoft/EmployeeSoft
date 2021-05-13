package com.example.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.File;

public class AWSConfig {
    private static AWSCredentials credentials;
    private static AmazonS3 s3client;
    private static String bucketName;

    static {
        credentials = new BasicAWSCredentials(
                "AKIASULWYEBDF43WV4CW",
                "AKIASULWYEBDF43WV4CW"
        );

        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-west-1")
                .build();

        bucketName = "employeefilebucket";
    }

    public static void uploadDocument(File file, String filename, String username) {
        // key: "username/filename"
        // key: "username/avatar-pic"
        s3client.putObject(bucketName, username + "/" + filename, file);
    }

    public static void getDocument(String filename) {
        s3client.getObject(bucketName, filename);
    }
}
