package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.ClassesDto;
import com.colorindomeudia.colorindomeudia.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    ClassesService classesService;

    @PostMapping("/new-class")
    public String createClass(@RequestBody ClassesDto classesDto) {
        return classesService.createClasses(classesDto);
    }
}
