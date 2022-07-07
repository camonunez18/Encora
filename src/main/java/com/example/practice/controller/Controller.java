package com.example.practice.controller;

import com.example.practice.model.TextOut;
import com.example.practice.model.TextIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.practice.service.TextService;

@RestController
public class Controller {
    
    private TextService service;
    
    @GetMapping()
    public ResponseEntity<TextOut> getTextAnalytics(
            @RequestBody TextIn text
    ) {
        service = new TextService();
        return new ResponseEntity<>(service.getAnalyticsText(text), HttpStatus.OK);
    }
}
