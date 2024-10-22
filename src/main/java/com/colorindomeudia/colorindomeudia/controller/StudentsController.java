package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.RequestStudentDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseStudentDto;
import com.colorindomeudia.colorindomeudia.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsService studentsService;

    @PostMapping("/add")
    public ResponseStudentDto createStudent(@RequestBody RequestStudentDto requestStudentDto) {
        return studentsService.createStudent(requestStudentDto);
    }

    @GetMapping("/all")
    public List<ResponseStudentDto> getListStudents() {
        return studentsService.getListStudents();
    }

    @GetMapping("/search/{id}")
    public ResponseStudentDto getStudentById(@PathVariable Long id) {
        return studentsService.getStudentById(id);
    }

}
