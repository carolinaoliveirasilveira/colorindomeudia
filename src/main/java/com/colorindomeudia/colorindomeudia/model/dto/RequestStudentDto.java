package com.colorindomeudia.colorindomeudia.model.dto;

import java.util.Date;

public record RequestStudentDto(
        String name,
        Date birthday,
        String grade,
        String parent_name,
        String address,
        String contact_number) {
}
