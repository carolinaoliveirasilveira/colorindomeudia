package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Classes;
import com.colorindomeudia.colorindomeudia.model.Enrollment;
import com.colorindomeudia.colorindomeudia.model.Students;
import com.colorindomeudia.colorindomeudia.model.dto.RequestEnrollementDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseEnrollementDto;
import com.colorindomeudia.colorindomeudia.repository.ClassesRepository;
import com.colorindomeudia.colorindomeudia.repository.EnrollmentRepository;
import com.colorindomeudia.colorindomeudia.repository.StudentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired(required = true)
    EnrollmentRepository enrollmentRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    StudentsRepository studentsRepository;

    public ResponseEnrollementDto createEnrollment(RequestEnrollementDto requestEnrollementDto) { // aceita um DTO de solicitação e retorna um DTO de resposta

        if (requestEnrollementDto.classesId() == null) { // verifica se o DTO é nulo
            throw new RuntimeException("O ID da classe não pode ser nulo."); // se o DTO for nulo retorna a exceção
        }

        Enrollment enrollment = toEnrollmentDto(requestEnrollementDto); // converte o DTO de solicitação em uma entidade de matrícula
        Enrollment returnEnrollment = enrollmentRepository.save(enrollment); // salva a matrícula
        return toConverteEnrollmentParaResponseEnrollmentDto(returnEnrollment);

    }

    private Enrollment toEnrollmentDto(RequestEnrollementDto requestEnrollementDto) { // metodo para converter o DTO de solicitação em uma entidade de matrícula
        Enrollment enrollment = new Enrollment(); // nova instância de matrícula

        enrollment.setEnrollmentDate(requestEnrollementDto.enrollmentDate()); // defini data e status da matricula usando os dados do DTO
        enrollment.setStatus(requestEnrollementDto.status());

        Long classesId = requestEnrollementDto.classesId(); // obtem o ID da classe e define na matrícula
        Classes classes = classesRepository.findById(classesId)
                .orElseThrow(() -> new EntityNotFoundException("Classe não existe!"));
        enrollment.setClasses(classes);

        Long studentId = requestEnrollementDto.studentId(); // obtem o ID do aluno e define na matrícula
        Students student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não existe!"));
        enrollment.setStudents(student);

        return enrollment; // retorna a matrícula
    }


    // metodo para converter a entidade de matricula em um DTO de resposta
    private ResponseEnrollementDto toConverteEnrollmentParaResponseEnrollmentDto(Enrollment enrollment) {
        ResponseEnrollementDto responseEnrollementDto = // nova instancia de resposta com os dados da matricula
                new ResponseEnrollementDto(
                        enrollment.getId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getStatus());

        return responseEnrollementDto;
    }

}

