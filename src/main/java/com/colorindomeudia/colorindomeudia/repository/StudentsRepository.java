package com.colorindomeudia.colorindomeudia.repository;

import com.colorindomeudia.colorindomeudia.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    List<Students> findByName(String name);


}

// herda todos os métodos básicos de CRUD (create, read, update e delete) para Students
// Long para indicar que os ID's dos alunos são do tipo Long