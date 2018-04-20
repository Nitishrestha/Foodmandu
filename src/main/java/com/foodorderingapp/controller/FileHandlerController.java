package com.foodorderingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("file")
public class FileHandlerController {
    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> test(@PathVariable String fileName) {
        try {
            return new ResponseEntity<>(Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\IMAGES\\" + fileName)), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
