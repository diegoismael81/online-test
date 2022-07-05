package com.online.test.onlinetest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.test.onlinetest.dto.NewQuestionDTO;
import com.online.test.onlinetest.dto.QuestionDTO;
import com.online.test.onlinetest.dto.QuestionExamDTO;
import com.online.test.onlinetest.services.QuestionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/exams")
public class QuestionController {
    
    final QuestionService service;

    public QuestionController(QuestionService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/questions")
    public ResponseEntity<QuestionDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewQuestionDTO questionDTO){
        QuestionDTO questioDTO = service.create(id, questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(questioDTO);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{idExam}/questions/{id}")
    public ResponseEntity<QuestionExamDTO> retrive(@PathVariable("idExam") Long idExam, @PathVariable("id") Long id){
        QuestionExamDTO result = service.retrieve(idExam, id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{idExam}/questions/{id}")
    public ResponseEntity<QuestionExamDTO> update(@RequestBody QuestionDTO questionDTO, @PathVariable("idExam") Long idExam, @PathVariable("id") Long id){
        QuestionExamDTO result = service.update(questionDTO, idExam, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{idExam}/questions/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idExam") Long idExam, @PathVariable("id") Long id){
        service.delete(idExam, id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionDTO>> list(@PathVariable("id") Long id){
        List<QuestionDTO> questions = service.list(id);
        return ResponseEntity.ok().body(questions);        
    }

}
