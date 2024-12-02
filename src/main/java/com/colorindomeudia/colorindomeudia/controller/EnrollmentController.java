package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.Students;
import com.colorindomeudia.colorindomeudia.model.dto.RequestEnrollementDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseEnrollementDto;
import com.colorindomeudia.colorindomeudia.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("/new-enrollment")
    public ResponseEnrollementDto createEnrollment(@RequestBody RequestEnrollementDto requestEnrollementDto) {
        return enrollmentService.createEnrollment(requestEnrollementDto);
    }

    @GetMapping("/all")
    public List<ResponseEnrollementDto> getListEnrollment() {
        return enrollmentService.getListEnrollment();
    }

    @GetMapping("/search/{id}")
    public ResponseEnrollementDto getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<byte[]> exportStudentsToCsv() throws IOException {
        List<Students> studentsList = enrollmentService.getListStudents();
        byte[] csvContent = enrollmentService.exportStudentsToCsv(studentsList);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.csv")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(csvContent);
    }



}
