package com.example.searchwordservice.service.impl;

import com.example.searchwordservice.dto.SearchDetails;
import com.example.searchwordservice.model.FileWordSearch;
import com.example.searchwordservice.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Upload file service.
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {


    @Value("${file.upload-dir}")
    private String fileDirectory;


    @Override
    public ResponseEntity<Object> uploadFile(MultipartFile file) throws IOException, ValidationException {
        File myFile = new File(fileDirectory + file.getOriginalFilename());
        System.out.println("filepath:" + myFile.getAbsolutePath());
        if (checkFileExtension(myFile.getAbsolutePath()))
            throw new ValidationException("File uploaded not allowed!");
        myFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(myFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return new ResponseEntity<Object>("File Uploaded Successfully", HttpStatus.OK);
    }

    @Override
    public List<SearchDetails> searchWord(FileWordSearch searchInput) throws IOException, ValidationException {
        File file = new File(fileDirectory + searchInput.getFileName());
        if (file == null || !file.exists()) {
            throw new ValidationException("File not found or File empty!!");
        }
        List<SearchDetails> searchDetailsList = new ArrayList<>();
        String[] words = null;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int lineNumber = 0;
        while ((line = br.readLine()) != null)
        {
            lineNumber++;
            words = line.split(" ");
            for (int i = 0; i < words.length; i++)
            {
                if (words[i].equals(searchInput.getInputWord()))
                    searchDetailsList.add(
                            SearchDetails
                                    .builder()
                                    .searchString(words[i])
                                    .lineNumber(lineNumber)
                                    .column(i + 1).build());
            }
        }

        fr.close();
        return searchDetailsList;
}


    @Override
    public List<SearchDetails> extractNumbers(FileWordSearch searchInput) throws IOException, ValidationException {
        File file = new File(fileDirectory + searchInput.getFileName());
        if (file == null || !file.exists()) {
            throw new ValidationException("File not found or File empty!!");
        }
        List<SearchDetails> searchDetailsList = new ArrayList<>();
        String[] words = null;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int lnNumber = 0;
        while ((line = br.readLine()) != null)
        {
            lnNumber++;
            words = line.split(" ");
            for (int i = 0; i < words.length; i++)
            {
                  if (isNumeric(words[i])) {
                      searchDetailsList.add(
                      SearchDetails
                              .builder()
                              .searchString(words[i])
                              .lineNumber(lnNumber)
                              .column(i + 1).build()
                              );
                }
            }
        }
        fr.close();
        return searchDetailsList;

    }

    private boolean checkFileExtension(String absolutePath) {
        var extensionList = new ArrayList<String>(Arrays.asList("xml", "txt", "html"));
        if (null == absolutePath || !absolutePath.contains(".")) {
            return true;
        } else {
            return !extensionList.contains(absolutePath.substring(absolutePath.lastIndexOf(".") + 1));
        }
    }


    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
