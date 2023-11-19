package com.modulosocios.ModuloSocios.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class AwsS3Service {

    private final Logger LOGGER = LoggerFactory.getLogger(AwsS3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public Object uploadFile(MultipartFile file) {
        try {
            File mainFile = new File(file.getOriginalFilename());
            FileOutputStream stream = new FileOutputStream(mainFile);
            stream.write(file.getBytes());
            String fileName = System.currentTimeMillis() + " - " + mainFile.getName();
            LOGGER.info("Subiendo archivo con el nombre: " + fileName);

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, mainFile);
            amazonS3.putObject(request);// Sube el archivo

            String description = "ARCHIVO CARGADO CON EXITO A S3 ";
            LOGGER.info(description + fileName);

            // Elimina la copia local
            stream.close();
            if (mainFile.delete()) {

                LOGGER.info("Copia local borrada");
            } else {

                LOGGER.error("Copia local no borrada");
            }

            return jsonBuild(fileName, description);

        } catch (AmazonServiceException | IOException e) {
            String response = "Error al cargar el archivo: " + e.getMessage();
            LOGGER.error(response, e);

            return jsonBuild(null, response);
        }
    }

    public List<String> getFilesFromS3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map(item -> {
            return item.getKey();
        }).collect(Collectors.toList());

        if (list.isEmpty()) {
            String response = "Lista Vacía";
            LOGGER.warn(response);

            return null;
        } else {
            return list;
        }
    }

    public InputStream downloadFile(String key) {
        try {
            boolean fileExists = amazonS3.doesObjectExist(bucketName, key);
            if (fileExists) {
                S3Object object = amazonS3.getObject(bucketName, key);
                String response = "Archivo encontrado con éxito ";
                LOGGER.info(response + key);

                return object.getObjectContent();

            } else {
                String response = "El archivo no existe ";
                LOGGER.warn(response + key);

                return null;

            }
        } catch (AmazonServiceException e) {
            String response = "Error al descargar el archivo: " + e.getMessage();
            LOGGER.error(response, e);

            return null;
        }
    }

    public Object deleteFile(String fileName) {
        try {
            boolean fileExists = amazonS3.doesObjectExist(bucketName, fileName);

            if (fileExists) {
                DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, fileName);
                amazonS3.deleteObject(deleteObjectRequest);
                String response = "Archivo eliminado con éxito de S3 ";
                LOGGER.info(response + fileName);

                return jsonBuild(fileName, response);
            } else {
                String response = "El archivo no existe ";
                LOGGER.warn(response + fileName);

                return jsonBuild(fileName, response);
            }

        } catch (AmazonServiceException e) {
            String response = "Error al eliminar el archivo: " + e.getMessage();
            LOGGER.error(response, e);

            return jsonBuild(null, response);
        }
    }

    public Object updateFile(MultipartFile newFile, String oldFileName) {
        try {
            // Verificar si el archivo realmente existe
            boolean fileExists = amazonS3.doesObjectExist(bucketName, oldFileName);

            if (fileExists) {
                String newFileName = System.currentTimeMillis() + " - " + newFile.getOriginalFilename();

                // Sube el nuevo archivo
                try {
                    File mainFile = new File(newFile.getOriginalFilename());
                    FileOutputStream stream = new FileOutputStream(mainFile);
                    stream.write(newFile.getBytes());

                    PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
                    amazonS3.putObject(request);// Sube el nuevo archivo

                    // Eliminar el archivo antiguo
                    deleteFile(oldFileName);

                    String response = "Archivo actualizado con éxito en S3 ";
                    LOGGER.info(response + " Nuevo nombre: " + newFileName);

                    // Elimina la copia local
                    stream.close();
                    if (mainFile.delete()) {
                        LOGGER.info("Copia local borrada");
                    } else {
                        LOGGER.error("Copia local no borrada");
                    }

                    return jsonBuild(newFileName, response);

                } catch (AmazonServiceException | IOException e) {
                    String response = "Error al subir el archivo: " + e.getMessage();
                    LOGGER.error(response, e);

                    return response;
                }

            } else {
                String response = "El archivo a actualizar no existe en S3: " + oldFileName;
                LOGGER.warn(response);

                return jsonBuild(null, response);
            }

        } catch (AmazonServiceException e) {
            String response = "Error al actualizar el archivo: " + e.getMessage();
            LOGGER.error(response, e);

            return jsonBuild(null, response);
        }
    }

    public Object jsonBuild(String value, String description) {

        Map<String, String> responseJson = new HashMap<>();
        responseJson.put("value", value);
        responseJson.put("description", description);

        return responseJson;
    }

}
