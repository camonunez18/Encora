package com.example.practice.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class TextOut {
    
    private long totalChars;
    private long totalNumericChars;
    private long totalAlphaChars;
    private long totalUpperCase;
    private long totalLowerCase;
    private long totalWhiteSpaces;
    private long punctuationSpaces;
    private long totalWords;
    private Map<String, Conteo> top10;
}
