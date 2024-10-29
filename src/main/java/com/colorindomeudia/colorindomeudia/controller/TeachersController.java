package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.RequestTeachersDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseTeachersDto;
import com.colorindomeudia.colorindomeudia.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeachersController {
    @Autowired
    TeachersService teachersService;

    @PostMapping("/add")
    public ResponseTeachersDto createTeacher(@RequestBody RequestTeachersDto requestTeachersDto) {
        return teachersService.createTeacher(requestTeachersDto);
    }

}
