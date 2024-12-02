package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.Students;
import com.colorindomeudia.colorindomeudia.model.dto.RequestClassesDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseClassesDto;
import com.colorindomeudia.colorindomeudia.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{classId}/students")
    public ResponseEntity<List<Students>> getStudentsByClassId(@PathVariable Long classId) {
        List<Students> studentsNames = classesService.getStudentsNames(classId);
        return ResponseEntity.ok(studentsNames);
    }


    @GetMapping("/{classId}/birthday-students/{month}")
    public List<Students> getBirthdayStudents(@PathVariable Long classId, @PathVariable int month)    {
        return classesService.getBirthdayStudents(classId, month);
    }

}
