package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.SectionCreateDTO;
import org.example.lmsbackend.dto.SectionResponseDTO;
import org.example.lmsbackend.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/api/sections")
    public ResponseEntity<List<SectionResponseDTO>> getSections(){
        return new ResponseEntity<>(sectionService.getSections(), HttpStatus.OK);
    }


    @PostMapping("/api/sections")
    public ResponseEntity<SectionResponseDTO> addSection(@RequestBody SectionCreateDTO dto){
        return new ResponseEntity<>(sectionService.addSection(dto),HttpStatus.CREATED);
    }

    @DeleteMapping("/api/sections/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable Long id){
        sectionService.deleteSection(id);
        return new ResponseEntity<>("Section id " + id +" has now deleted!!", HttpStatus.OK);
    }

    @PutMapping("/api/sections/{id}")
    public ResponseEntity<SectionResponseDTO> updateSection(
            @PathVariable Long id,
            @RequestBody SectionCreateDTO dto){
        return new ResponseEntity<>(sectionService.updateSection(id,dto),HttpStatus.OK);
    }

    //    @GetMapping("/api/sections/{id}")
//    public ResponseEntity<SectionDetailDTO> getSection(@PathVariable Long id){
//        return new ResponseEntity<>(sectionService.getSection(id),HttpStatus.OK);
//    }
}
