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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.test.onlinetest.dto.NewOptionDTO;
import com.online.test.onlinetest.dto.OptionDTO;
import com.online.test.onlinetest.services.OptionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/exams")
public class OptionController {
    
    final OptionService service;

    public OptionController(OptionService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/questions/{idQuestion}/options")
    public ResponseEntity<List<OptionDTO>> create(@PathVariable("id") Long id, @PathVariable("idQuestion") Long idQuestion, @Valid @RequestBody List<NewOptionDTO> optionsDTO){
        List<OptionDTO> optionDTOs = service.create(id, idQuestion, optionsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(optionDTOs);        
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}/questions/{idQuestion}/options")
    public ResponseEntity<List<OptionDTO>> delete(@PathVariable("id") Long id, @PathVariable("idQuestion") Long idQuestion){
        service.remove(id, idQuestion);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/questions/{idQuestion}/options")
    public ResponseEntity<List<OptionDTO>> list(@PathVariable("id") Long id, @PathVariable("idQuestion") Long idQuestion){
        List<OptionDTO> optionDTOs = service.list(id, idQuestion);
        return ResponseEntity.status(HttpStatus.OK).body(optionDTOs);        
    }

}
