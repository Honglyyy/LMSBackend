package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.QuizCreateDTO;
import org.example.lmsbackend.dto.QuizResponseDTO;
import org.example.lmsbackend.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/api/quizzes")
    public ResponseEntity<List<QuizResponseDTO>> getQuizzes(){
        return new ResponseEntity<>(quizService.getQuizzes(), HttpStatus.OK);
    }


    @PostMapping("/api/quizzes")
    public ResponseEntity<QuizResponseDTO> addQuiz(@RequestBody QuizCreateDTO dto){
        return new ResponseEntity<>(quizService.addQuiz(dto),HttpStatus.CREATED);
    }

    @PutMapping("/api/quizzes/{id}")
    public ResponseEntity<QuizResponseDTO> updateQuiz(
            @PathVariable Long id,
            @RequestBody QuizCreateDTO dto
    ){
        return new ResponseEntity<>(quizService.updateQuiz(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id){
        quizService.deleteQuiz(id);
        return new ResponseEntity<>("Quiz id " + id + " is now deleted!!", HttpStatus.OK);
    }
}
