package com.colorindomeudia.colorindomeudia.repository;

import com.colorindomeudia.colorindomeudia.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query(
            value = "SELECT * FROM Classes e WHERE e.id = :classId",
            nativeQuery = true)
    Classes findListingStudentsInClass(@Param("classId") Long classId);
}



//@Query serve para definir consultas personalizadas do repositório
// SELECT students está buscando students
// FROM Enrollment e: indica a entidade que buscamois os dados, no caso enrollment
// WHERE filtra os resultados com base em condições especifícas
// e.classes.id = :classId: verifica que o ID da classe corresponde ao parametro classId
// o ":" indica que classId é um parametro que será passado na consulta
// findStudentsByClassId: metodo que procura estudantes de uma classe especifica, indicada pelo ID da classe
// @Param("classId"): anotação que informa que o classId deve ser ligado ao parametro do query, o :classId
// Long classId: entrada do metodo, representa o ID da classe que está buscando students

// o metodo findStudentsByClassId usa a anotação @Query para fazer uma consulta
// personalizada que seleciona os estudantes da entidade enrollment, filtrando
// pelo ID da classe, ele aceita o parametro classId e retorna uma lista de objetos
// students que correspondem ao ID