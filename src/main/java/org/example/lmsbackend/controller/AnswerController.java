package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.AnswerCreateDTO;
import org.example.lmsbackend.dto.AnswerResponseDTO;
import org.example.lmsbackend.model.Answers;
import org.example.lmsbackend.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController

public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/api/answers")
    ResponseEntity<AnswerResponseDTO> addAnswer(@RequestBody AnswerCreateDTO dto){
        return new ResponseEntity<>(answerService.addAnswer(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/answers/{id}")
    ResponseEntity<Answers> updateAnswer(
            @PathVariable Long id,
            @RequestBody AnswerCreateDTO dto){
        return new ResponseEntity<>(answerService.updateAnswer(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("/api/answers/{id}")
    ResponseEntity<String> deleteAnswer(@PathVariable Long id ){
        answerService.deleteAnswer(id);
        return new ResponseEntity<>("Answer with id " + id + " is deleted successfully!!", HttpStatus.OK);
    }

}
