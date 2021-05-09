package com.example.searchwordservice.service;

import com.example.searchwordservice.dto.SearchDetails;
import com.example.searchwordservice.model.FileWordSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * The interface Upload file service.
 */
public interface UploadFileService {
    /**
     * Upload file response entity.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException         the io exception
     * @throws ValidationException the validation exception
     */
    ResponseEntity<Object> uploadFile(MultipartFile file) throws IOException, ValidationException;

    /**
     * Search word list.
     *
     * @param searchInput the search input
     * @return the list
     * @throws IOException         the io exception
     * @throws ValidationException the validation exception
     */
    List<SearchDetails> searchWord(FileWordSearch searchInput) throws IOException, ValidationException;

    List<SearchDetails> extractNumbers(FileWordSearch searchInput) throws IOException, ValidationException;

}
