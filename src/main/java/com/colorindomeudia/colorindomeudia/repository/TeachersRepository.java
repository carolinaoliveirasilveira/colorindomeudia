package com.colorindomeudia.colorindomeudia.repository;

import com.colorindomeudia.colorindomeudia.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachersRepository extends JpaRepository<Teachers, Long> {

    List<Teachers> findByName(String name);
}
