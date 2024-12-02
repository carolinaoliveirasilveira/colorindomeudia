package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.RequestStudentDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseStudentDto;
import com.colorindomeudia.colorindomeudia.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseStudentDto createStudent(@RequestBody RequestStudentDto requestStudentDto) {
        return studentService.createStudent(requestStudentDto);
    }

    @GetMapping("/all")
    public List<ResponseStudentDto> getListStudents() {
        return studentService.getListStudents();
    }

    @GetMapping("/search/{id}")
    public ResponseStudentDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }




}
