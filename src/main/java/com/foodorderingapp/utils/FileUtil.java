package com.foodorderingapp.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class FileUtil {
    public static MultipartFile getFile(MultipartHttpServletRequest request) {
        MultipartFile file = null;
        List<MultipartFile> files = request.getFiles("files");
        if (files.size() == 0 || files.isEmpty()) {
            try {
                file = getEmptyImage();
                return file;
            } catch (Exception e) {
                e.getMessage();
            }
        } else
            file = files.get(0);
        return file;
    }

    public static MultipartFile getEmptyImage() throws Exception {
        File file = new File("C:\\Users\\User\\Desktop\\IMAGES\\No Image.png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("NOIMAGE",
                file.getName(), "image/png", IOUtils.toByteArray(input));
        return multipartFile;
    }
}
