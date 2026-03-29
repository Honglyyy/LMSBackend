package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.SectionResponseDTO;
import org.example.lmsbackend.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
