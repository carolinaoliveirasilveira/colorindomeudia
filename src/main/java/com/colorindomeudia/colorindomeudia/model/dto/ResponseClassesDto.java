package com.colorindomeudia.colorindomeudia.model.dto;

import java.util.List;

public record ResponseClassesDto(
        Long classId,
        String level,
        String teacher,
        String session,
        String description) {


}
