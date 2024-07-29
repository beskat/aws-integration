package com.aws.controller;

import com.aws.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    /**
     * Endpoint to upload a file to S3.
     *
     * @param file the file to be uploaded
     * @return ResponseEntity with upload status
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            s3Service.uploadFile(file.getOriginalFilename(), file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to upload file: " + e.getMessage());
        }
    }

    /**
     * Endpoint to download a file from S3.
     *
     * @param keyName      the key name of the file to be downloaded
     * @param downloadPath the path where the file will be downloaded
     * @return ResponseEntity with download status
     */
    @GetMapping("/download")
    public ResponseEntity<String> downloadFile(@RequestParam("keyName") String keyName,
                                               @RequestParam("downloadPath") String downloadPath) {
        try {
            s3Service.downloadFile(keyName, downloadPath);
            return ResponseEntity.ok("File downloaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to download file: " + e.getMessage());
        }
    }
}
