package com.example.searchwordservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Search details.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDetails {

    private String searchString;
    private Integer lineNumber;
    private Integer column;
}
