package com.modulosocios.ModuloSocios.interfaces;

import java.io.InputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AwsS3ServiceInterface {
    Object uploadFile(MultipartFile file);

    List<String> getFilesFromS3();

    InputStream downloadFile(String key);

    Object deleteFile(String fileName);

    Object updateFile(MultipartFile newFile, String oldFileName);

}
