package com.colorindomeudia.colorindomeudia.controller;

import com.colorindomeudia.colorindomeudia.model.dto.PreEnrollmentDto;
import com.colorindomeudia.colorindomeudia.model.dto.RequestPreEnrollmentDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponsePreEnrollmentDto;
import com.colorindomeudia.colorindomeudia.service.PreEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pre-enrollment")
public class PreEnrollmentController {
    @Autowired
    PreEnrollmentService preEnrollmentService;

    @PostMapping("/new-pre-enrollment")
    public ResponsePreEnrollmentDto createPreEnrollment(@RequestBody RequestPreEnrollmentDto requestPreEnrollmentDto) {
        return preEnrollmentService.createPreEnrollment(requestPreEnrollmentDto);
    }

    @GetMapping("/{desiredClassId}/students/{status}")
    public List<PreEnrollmentDto> getPreEnrollmentStudents(@PathVariable Long desiredClassId, @PathVariable String status) {
        return preEnrollmentService.getPreEnrollmentStudents(desiredClassId, status);
 }

 @GetMapping("/{preEnrollmentId}")
    public ResponsePreEnrollmentDto getPreEnrollmentById(@PathVariable Long preEnrollmentId) {
     return preEnrollmentService.getPreEnrollmentById(preEnrollmentId);
 }

}
