package com.online.test.onlinetest.controllers;

import java.util.List;

import javax.validation.Valid;

import com.online.test.onlinetest.dto.ExamDTO;
import com.online.test.onlinetest.dto.ExamListDTO;
import com.online.test.onlinetest.dto.NewExamDTO;
import com.online.test.onlinetest.services.ExamService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/exams")
public class ExamenController {
    private final ExamService service;
  
    public ExamenController(ExamService srv){
        this.service =srv;
    }
    
    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<ExamDTO> create(@Valid @RequestBody NewExamDTO examDTO){
        ExamDTO result = service.create(examDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> retrive(@PathVariable("id") Long id){
        ExamDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@RequestBody ExamDTO examDTO, @PathVariable("id") Long id){
        ExamDTO result = service.update(examDTO, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<ExamListDTO>> list(@PathVariable("page") int page, 
        @PathVariable("size") int size,
        @RequestParam(name = "sort", required = false) String sort){
        List<ExamListDTO> result  = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);        
    }

     /* ================ COUNT ================ */
     @GetMapping("/count")
     public ResponseEntity<Long> count(){
         long result = service.count();
         return ResponseEntity.ok().body(result);        
     }

}
