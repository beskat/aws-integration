package com.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    /**
     * Uploads a file to the specified S3 bucket.
     *
     * @param keyName the key name for the file in S3
     * @param file    the file to be uploaded
     * @throws IOException if an I/O error occurs
     */
    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        s3client.putObject(bucketName, keyName, file.getInputStream(), null);
    }

    /**
     * Downloads a file from the specified S3 bucket.
     *
     * @param keyName      the key name of the file in S3
     * @param downloadPath the path where the file will be downloaded
     */
    public void downloadFile(String keyName, String downloadPath) {
        final var getObjectRequest = new GetObjectRequest(bucketName, keyName);
        s3client.getObject(getObjectRequest, new File(downloadPath));
    }
}
