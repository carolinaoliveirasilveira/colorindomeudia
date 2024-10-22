package com.colorindomeudia.colorindomeudia.model.dto;

public record ResponseStudentDto(
        Long id,
        String name,
        String age,
        String grade,
        String parent_name,
        String address,
        String contact_number) {
}
