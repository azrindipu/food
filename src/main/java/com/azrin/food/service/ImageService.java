package com.azrin.food.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Value("${fire.base.bucket.name}")
    private String bucketName;

    @Value("${fire.base.project.id}")
    private String projectId;

    @Value("${fire.base.json.file.name}")
    private String jsonFileName;

    private final String imageContentType = "image/jpeg";

    private StorageOptions storageOptions;

    @PostConstruct
    private void initializeFirebase(){
        try {
            logger.info("==========> Creating storageOptions object <==========");
            File file = new ClassPathResource(jsonFileName).getFile();
            FileInputStream serviceAccount =
                    new FileInputStream(file.getPath());
            this.storageOptions = StorageOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            logger.info("==========> Created storageOptions object <==========");
        }catch (Exception e){
            logger.info("==========> Exception in creating storageObjects <==========");
            logger.info(e.getMessage());
        }
    }

    public String uploadImage(File imageFileLocation) throws Exception {
        logger.info("==========> Start uploading image <==========");
        String uniqueString = UUID.randomUUID().toString();
        Path filePath = imageFileLocation.toPath();
        String imageName = uniqueString+imageFileLocation.getName();
        Storage storage = storageOptions.getService();
        BlobId blobId = BlobId.of(bucketName, imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageContentType).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));
        logger.info("==========> Image uploaded <==========");
        return blob.getSelfLink();
    }
}
