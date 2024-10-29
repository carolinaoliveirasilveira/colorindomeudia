package com.colorindomeudia.colorindomeudia.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // anotação para informar que essa classe é uma entidade do banco de dados
@Table(name = "students") // anotação para especificar o nome da tabela no banco de dados
public class Students {
    @Id // anotação para identificar a chave primaria de uma entidade no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) // junto com o id para gerar valores automáticos para chave primária
    private Long id;
    private String name;
    private String age;
    private String grade;
    private String parent_name;
    private String address;
    private String contact_number;

    @OneToMany(mappedBy = "students")
    private List<Enrollment> enrollments = new ArrayList<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
