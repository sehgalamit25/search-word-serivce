package com.example.searchwordservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type File word search.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileWordSearch {
    private String inputWord;
    private String fileName;
}
