package com.mcb.abdulbasit.valuation.service;

import com.mcb.abdulbasit.valuation.model.File;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Async
@Service
public class FileService {

    public void fileUpload(MultipartFile[] files){
        // Handle empty file error
        if (files.length == 0) {
        //    return CompletableFuture
        //            .completedFuture(ResponseEntity.badRequest().body("No files submitted"));
        }
        // File upload process is submitted
        else {

            for (MultipartFile file : files) {
                saveFile(file);
                //TODO: access and store each file into file storage
            }
        //    return CompletableFuture.completedFuture(
        //            ResponseEntity.ok("File upload started"));
        }
    }
    /**
     * saveFile
     * @return
     */
    @SneakyThrows
    @Async
    public void saveFile(MultipartFile file) {

        Thread.sleep(new Random().nextLong(4000, 8000));
        System.out.println(file.getOriginalFilename() + " is uploaded at " + LocalDateTime.now());
    }
}
