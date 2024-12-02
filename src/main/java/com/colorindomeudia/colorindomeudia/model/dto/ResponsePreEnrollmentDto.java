package com.colorindomeudia.colorindomeudia.model.dto;

import java.util.Date;

public record ResponsePreEnrollmentDto(
        Long desiredClassId,
        Date preEnrollmentDate,
        String status,
        String studentName,
        String studentEmail,
        String studentPhone) {
}
