package com.colorindomeudia.colorindomeudia.model.dto;

import java.util.Date;

public record ResponseStudentDto(
        Long id,
        String name,
        Date birthday,
        String grade,
        String parent_name,
        String address,
        String contact_number) {
}
