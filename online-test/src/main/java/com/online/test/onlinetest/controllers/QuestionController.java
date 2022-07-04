package com.online.test.onlinetest.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.test.onlinetest.dto.NewQuestionDTO;
import com.online.test.onlinetest.services.QuestionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/questions")
public class QuestionController {
    
    final QuestionService service;

    public QuestionController(QuestionService srv){
        this.service = srv;
    }


    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody NewQuestionDTO questionDTO){
        service.create(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Question was added successfully");        
    }

}
