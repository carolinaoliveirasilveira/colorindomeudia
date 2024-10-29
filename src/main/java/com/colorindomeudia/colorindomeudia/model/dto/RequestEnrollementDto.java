package com.colorindomeudia.colorindomeudia.model.dto;

public record RequestEnrollementDto(
        Long classesId,
        Long studentId,
        String enrollmentDate,
        String status) {

}
