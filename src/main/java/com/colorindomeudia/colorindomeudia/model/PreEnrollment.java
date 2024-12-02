package com.colorindomeudia.colorindomeudia.model;

import com.colorindomeudia.colorindomeudia.util.StatusPay;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PREENROLLMENT")
public class PreEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PreEnrollmentId;
    private Date preEnrollmentDate;
    private StatusPay status;
    private String studentName;
    private String studentEmail;
    private String studentPhone;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private Students students;

    @ManyToOne
    @JoinColumn(name = "desired_class_id", nullable = false)
    private Classes desiredClass;

    public Classes getDesiredClass() {
        return desiredClass;
    }

    public void setDesiredClass(Classes desiredClass) {
        this.desiredClass = desiredClass;
    }

    public Date getPreEnrollmentDate() {
        return preEnrollmentDate;
    }

    public void setPreEnrollmentDate(Date preEnrollmentDate) {
        this.preEnrollmentDate = preEnrollmentDate;
    }

    public Long getPreEnrollmentId() {
        return PreEnrollmentId;
    }

    public void setPreEnrollmentId(Long preEnrollmentId) {
        PreEnrollmentId = preEnrollmentId;
    }

    public StatusPay getStatus() {
        return status;
    }

    public void setStatus(StatusPay status) {
        this.status = status;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
    @JsonIgnore
    public Students getStudents() {
        return students;
    }
    @JsonIgnore
    public void setStudents(Students students) {
        this.students = students;
    }



}
