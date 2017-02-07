package com.ote.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private IFileIntegrationService fileIntegrationService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity test() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(@RequestParam(value = "file") MultipartFile file) {

        if (file == null) {
            log.error("Incoming file should not be null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the filename and build the local file path
            String fileName = file.getOriginalFilename();
            log.debug(String.format("New file %s incoming", fileName));
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                fileIntegrationService.persist(fileName, fileReader);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
