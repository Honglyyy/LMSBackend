package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.LessonCreateDTO;
import org.example.lmsbackend.dto.LessonDetailDTO;
import org.example.lmsbackend.dto.LessonResponseDTO;
import org.example.lmsbackend.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonController {


    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/api/lessons")
    public ResponseEntity<List<LessonResponseDTO>> getLessons(){
        return new ResponseEntity<>(lessonService.getLessons(), HttpStatus.OK);
    }

    @GetMapping("/api/lessons/{id}")
    public ResponseEntity<LessonDetailDTO> getLesson(@PathVariable Long id){
        return new ResponseEntity<>(lessonService.getLesson(id), HttpStatus.OK);
    }

    @PostMapping("/api/lessons")
    public ResponseEntity<LessonResponseDTO> addLesson(@RequestBody LessonCreateDTO dto){
        return new ResponseEntity<>(lessonService.addLesson(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/lessons/{id}")
    public ResponseEntity<LessonResponseDTO> updateLesson(
            @PathVariable Long id,
            @RequestBody LessonCreateDTO dto
    ){
        return new ResponseEntity<>(lessonService.updateLesson(id,dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/lessons/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long id){
        lessonService.deleteSection(id);
        return new ResponseEntity<>("Lesson id " + id + " has now deleted!!", HttpStatus.OK);
    }
}
