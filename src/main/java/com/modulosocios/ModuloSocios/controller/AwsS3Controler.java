package com.modulosocios.ModuloSocios.controller;

import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.modulosocios.ModuloSocios.interfaces.AwsS3ServiceInterface;

@RestController
@RequestMapping("/file")
public class AwsS3Controler {

    private final AwsS3ServiceInterface awsS3Service;

    public AwsS3Controler(AwsS3ServiceInterface awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        Object response = awsS3Service.uploadFile(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<String>> getFilesFromS3() {
        return new ResponseEntity<List<String>>(awsS3Service.getFilesFromS3(), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("key") String key) {
        InputStreamResource resource = new InputStreamResource(awsS3Service.downloadFile(key));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .body(resource);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteFile(@RequestParam("key") String key) {
        Object response = awsS3Service.deleteFile(key);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateFile(@RequestPart(value = "newFile") MultipartFile newFile,
            @RequestParam("oldKey") String oldFileName) {
        Object response = awsS3Service.updateFile(newFile, oldFileName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
