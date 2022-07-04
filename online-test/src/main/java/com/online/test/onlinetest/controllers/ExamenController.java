package com.online.test.onlinetest.controllers;

import java.util.List;

import javax.validation.Valid;

import com.online.test.onlinetest.dto.ExamDTO;
import com.online.test.onlinetest.dto.ExamListDTO;
import com.online.test.onlinetest.dto.NewExamDTO;
import com.online.test.onlinetest.dto.QuestionDTO;
import com.online.test.onlinetest.services.ExamService;
import com.online.test.onlinetest.services.QuestionService;

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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/exams")
public class ExamenController {
    private final ExamService service;
    private final QuestionService serviceQuestion;
  
    public ExamenController(ExamService srv, QuestionService srvQ){
        this.service =srv;
        this.serviceQuestion = srvQ;
    }

    @PostMapping()
    public ResponseEntity<ExamDTO> create(@Valid @RequestBody NewExamDTO examDTO){
        ExamDTO result = service.create(examDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> retrive(@PathVariable("id") Long id){
        ExamDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    @GetMapping() //el verbo es diferente a create ya que va
    public ResponseEntity<List<ExamListDTO>> list(){
        List<ExamListDTO> result  = service.list();
        return ResponseEntity.ok().body(result);        
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@RequestBody ExamDTO examDTO, @PathVariable("id") Long id){
        ExamDTO result = service.update(examDTO, id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Exam deleted!");        
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionDTO>> listQuestions(@PathVariable("id") Long id){
        List<QuestionDTO> questions = serviceQuestion.list(id);
        return ResponseEntity.ok().body(questions);        
    }



}
