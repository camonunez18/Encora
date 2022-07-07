package com.example.practice.service;

import com.example.practice.model.Conteo;
import com.example.practice.model.TextIn;
import com.example.practice.model.TextOut;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TextService {
    public TextOut getAnalyticsText(TextIn text) {
        
        long totalChars = text.getText().codePoints().count();
        long totalNumbers = text.getText().codePoints().mapToObj(x -> (char) x).filter(Character::isDigit).count();
        long totalLetters = text.getText().codePoints().mapToObj(x -> (char) x).filter(Character::isLetter).count();
        long totalLowerCase = text.getText().codePoints().mapToObj(x -> (char) x).filter(Character::isLowerCase).count();
        long totalUpperCase = text.getText().codePoints().mapToObj(x -> (char) x).filter(Character::isUpperCase).count();
        long totalWhiteSpaces = text.getText().codePoints().mapToObj(x -> (char) x).filter(Character::isSpaceChar).count();
        long totalPunctuation = text.getText().codePoints().mapToObj(x -> (char) x)
                .filter(x -> !Character.isLetterOrDigit(x) && !Character.isSpaceChar(x))
                .count();
        long totalWords = Arrays.stream(text.getText().split(" ")).count();
        HashMap<String, Long> countMap = new HashMap<>(10);
        
        text.getText().codePoints().mapToObj(x -> (char) x).filter(Character::isLetter).forEach(ch -> {
            long totalLetter = text.getText().codePoints().mapToObj(x -> (char) x).filter(letter -> letter.equals(ch)).count();
            countMap.put(
                    ch.toString(),
                    totalLetter
            );
        });
    
        LinkedHashMap<String, Conteo> mapTop10 = new LinkedHashMap<>(10);
        countMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEachOrdered(x ->
                        mapTop10.put(
                                x.getKey(),
                                Conteo.builder()
                                        .count(x.getValue())
                                        .percentage((x.getValue().doubleValue() * 100d) / (double) totalLetters)
                                        .build()
                        )
                );
        
        
        return TextOut.builder()
                .totalChars(totalChars)
                .totalNumericChars(totalNumbers)
                .totalAlphaChars(totalLetters)
                .totalLowerCase(totalLowerCase)
                .totalUpperCase(totalUpperCase)
                .totalWhiteSpaces(totalWhiteSpaces)
                .punctuationSpaces(totalPunctuation)
                .totalWords(totalWords)
                .top10(mapTop10)
                .build();
    }
}
