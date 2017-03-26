package com.ote.file.controller;

import com.ote.file.service.IFileIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    private IFileIntegrationService fileIntegrationService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(@RequestParam(value = "file") MultipartFile file) {

        if (file == null) {
            log.error("Incoming files should not be null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the filename and build the local files path
            String fileName = file.getOriginalFilename();
            log.debug(String.format("New files %s incoming", fileName));
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                long count = fileIntegrationService.persist(fileReader);
                log.debug(String.format("File %s has been saved in database. Count of rows: %d", fileName, count));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
