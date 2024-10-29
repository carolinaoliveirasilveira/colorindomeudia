package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.RequestClassesDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseClassesDto;
import com.colorindomeudia.colorindomeudia.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    ClassesService classesService;

    @PostMapping("/new-class")
    public ResponseClassesDto createClass(@RequestBody RequestClassesDto requestClassesDto) {
        return classesService.createClasses(requestClassesDto);
    }

    @GetMapping("/all-classes")
    public List<ResponseClassesDto> getListClasses() {
        return classesService.getListClasses();
    }
}
