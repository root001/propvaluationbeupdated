package com.mcb.abdulbasit.valuation.service;

import com.mcb.abdulbasit.valuation.enums.DocType;
import com.mcb.abdulbasit.valuation.model.File;
import com.mcb.abdulbasit.valuation.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    Random random = new Random();
    private final Path rootLocation = Paths.get("upload-dir".concat("\\").concat(String.valueOf(random.nextInt())));

    public FileService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public List<File> fileUpload(MultipartFile[] files, String[] docType){
        // Handle empty file error
        if (files.length == 0) {
            throw new IllegalArgumentException("no file");
        }

        List<File> fileMetadataList = new ArrayList<>();
        int i = 0;
        for (MultipartFile file : files) {
            String filePath = store(file);
            File fileMetadata = File.builder()
                    .name(file.getOriginalFilename())
                    .filePath(filePath)
                    .size(file.getSize())
                    .extension(file.getContentType())
                    .docType(DocType.valueOf(docType[i]))
                    .build();

            fileMetadataList.add(fileRepository.save(fileMetadata));
            i++;
        }
        return fileMetadataList;
    }
    /**
     * saveFile
     * @return
     */
    public String store(MultipartFile file) {
        try {
            Path destinationFile = rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile);
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
