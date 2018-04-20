package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.exception.StorageException;
import com.foodorderingapp.utils.StorageProperties;
import com.foodorderingapp.service.StorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final Path locationFE;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.locationFE = Paths.get(properties.getLocationFE());
    }

    @Override
    public void store(MultipartFile file, String newFileName) {
        String filename = newFileName;
        try {
            if (!(file.getContentType().equals("image/jpeg") ||
                    file.getContentType().equals("image/png")) ||
                    file.getContentType().equals("image/gif")) {
                throw new StorageException("Please select an image file to upload!");
            }
            if (!new File(rootLocation.toString()).exists()) {
                new File(rootLocation.toString()).mkdirs();
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file.getInputStream(), this.locationFE.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("==================================");
            System.out.println("File stored successfully with filename " + filename + " !");
            System.out.println("==================================");
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public MultipartFile getEmptyImage() throws Exception {
        File file = new File("C:\\Users\\User\\Desktop\\IMAGES\\No Image.png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("No Image.png",
                file.getName(), "image/png", IOUtils.toByteArray(input));
        return multipartFile;
    }
}
