package com.colorindomeudia.colorindomeudia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // anotação para informar que essa classe é uma entidade do banco de dados
@Table(name = "students") // anotação para especificar o nome da tabela no banco de dados
public class Students {
    @Id // anotação para identificar a chave primaria de uma entidade no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // junto com o id para gerar valores automáticos para chave primária
    private Long id;
    private String name;
    private Date birthday;
    private String grade;
    private String parent_name;
    private String address;
    private String contact_number;

    @OneToMany(mappedBy = "students")
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "students")
    @JsonBackReference
    private List<PreEnrollment> preEnrollments;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
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