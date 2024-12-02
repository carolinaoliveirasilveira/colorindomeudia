package com.colorindomeudia.colorindomeudia.model.dto;

public class PreEnrollmentDto {
    private Long studentId;
    private String studentName;
    private String status;

    public PreEnrollmentDto(Long id, String name, String status) {
        this.studentId = id;
        this.studentName = name;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
