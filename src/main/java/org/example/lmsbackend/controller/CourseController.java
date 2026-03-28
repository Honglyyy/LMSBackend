package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.CourseCreateDTO;
import org.example.lmsbackend.dto.CourseResponseDTO;
import org.example.lmsbackend.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/api/courses")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/api/courses/{id}")
    public ResponseEntity<CourseResponseDTO> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseService.getCourse(id),HttpStatus.OK);
    }

    @PostMapping("/api/courses")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseCreateDTO dto){
        return new ResponseEntity<>(courseService.addCourse(dto),HttpStatus.CREATED);
    }

    @DeleteMapping("/api/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course id " + id + " has now deleted!!", HttpStatus.OK);
    }

    @PutMapping("api/courses/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseCreateDTO dto
    ) {
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }
}
