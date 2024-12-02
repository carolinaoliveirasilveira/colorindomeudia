package com.colorindomeudia.colorindomeudia.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;
    private String teacher;
    private String session;
    private String description;

    @OneToMany(mappedBy = "classes")
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "desiredClass")
    private List<PreEnrollment> preEnrollments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;

    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<PreEnrollment> getPreEnrollments() {
        return preEnrollments;
    }

    public void setPreEnrollments(List<PreEnrollment> preEnrollments) {
        this.preEnrollments = preEnrollments;
    }
}


