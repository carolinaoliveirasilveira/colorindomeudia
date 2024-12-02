package com.colorindomeudia.colorindomeudia.model.dto;

import java.util.Date;

public record RequestPreEnrollmentDto(
        Long PreEnrollmentId,
        Date preEnrollmentDate,
        String desiredClass,
        String status,
        String studentName,
        String studentEmail,
        String studentPhone,
        Long classesId,
        Long studentId) {
}
