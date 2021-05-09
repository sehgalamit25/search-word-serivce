package com.example.searchwordservice.controller;

import com.example.searchwordservice.dto.SearchDetails;
import com.example.searchwordservice.model.FileWordSearch;
import com.example.searchwordservice.service.UploadFileService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;

/**
 * The type Upload file controller.
 */
@RestController
@RequestMapping(value = "/searchWord")
@Data
public class UploadFileController {

    private final UploadFileService uploadFileService;

    /**
     * Instantiates a new Upload file controller.
     *
     * @param uploadFileService the upload file service
     */
    @Autowired
    public UploadFileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }


    /**
     * Upload file response entity.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException         the io exception
     * @throws ValidationException the validation exception
     */
    @PostMapping(path = "/uploadFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestParam("File") MultipartFile file ) throws IOException, ValidationException {
        if (file == null || file.isEmpty()) {
            throw new ValidationException("File not found or File empty!!");
        }
        return uploadFileService.uploadFile(file);

    }

    /**
     * Check status response entity.
     *
     * @return the response entity
     */
    @GetMapping(value ="/checkURL", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkStatus()
    {
        return new ResponseEntity<>("Get URL Working", HttpStatus.OK);
    }

    /**
     * Search word list.
     *
     * @param searchInput the search input
     * @return the list
     * @throws IOException         the io exception
     * @throws ValidationException the validation exception
     */
    @PostMapping(path = "/searchWord", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SearchDetails> searchWord(@RequestBody FileWordSearch searchInput) throws IOException, ValidationException {
        return uploadFileService.searchWord(searchInput);
    }

    @PostMapping(path = "/extractNumbers", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SearchDetails> extractNumbers(@RequestBody FileWordSearch searchInput) throws IOException, ValidationException {
        return uploadFileService.extractNumbers(searchInput);
    }


}
