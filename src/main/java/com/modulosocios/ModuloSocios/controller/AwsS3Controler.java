package com.modulosocios.ModuloSocios.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import com.modulosocios.ModuloSocios.services.AwsS3Service;


@RestController
@RequestMapping("/file")
@Tag(name = "S3 Controller", description = "Endpoints used for connecting with Amazon AWS S3")
public class AwsS3Controler {

    private final AwsS3Service awsS3Service;

    public AwsS3Controler(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @Operation(
            summary = "Upload file to S3",
            description = "Post a multipart file to Amazon AWS S3 and store it there",
            tags = { "S3 Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        Object response = awsS3Service.uploadFile(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Get files from S3",
            description = "Get list of URLs from Amazon AWS S3 for further retrieval",
            tags = { "S3 Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/get")
    public ResponseEntity<List<String>> getFilesFromS3() {
        return new ResponseEntity<List<String>>(awsS3Service.getFilesFromS3(), HttpStatus.OK);
    }

    @Operation(
            summary = "Download file from S3",
            description = "Download a single file from Amazon AWS S3",
            tags = { "S3 Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Resource.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("key") String key) {
        InputStreamResource resource = new InputStreamResource(awsS3Service.downloadFile(key));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .body(resource);
    }

    @Operation(
            summary = "Delete file from S3",
            description = "Delete a single file from Amazon AWS S3",
            tags = { "S3 Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteFile(@RequestParam("key") String key) {
        Object response = awsS3Service.deleteFile(key);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Update file from S3",
            description = "Update a single file from Amazon AWS S3",
            tags = { "S3 Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/update")
    public ResponseEntity<Object> updateFile(@RequestPart(value = "newFile") MultipartFile newFile,
            @RequestParam("oldKey") String oldFileName) {
        Object response = awsS3Service.updateFile(newFile, oldFileName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
