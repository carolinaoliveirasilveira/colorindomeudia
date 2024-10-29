package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.RequestEnrollementDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseEnrollementDto;
import com.colorindomeudia.colorindomeudia.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("/new-enrollment")
    public ResponseEnrollementDto createEnrollment(@RequestBody RequestEnrollementDto requestEnrollementDto) {
        return enrollmentService.createEnrollment(requestEnrollementDto);
    }

}
