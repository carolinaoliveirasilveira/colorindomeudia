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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
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


    private ResponseEnrollementDto toConverteEnrollmentParaResponseEnrollmentDto(Enrollment enrollment) {
        ResponseEnrollementDto responseEnrollementDto = // nova instancia de resposta com os dados da matricula
                new ResponseEnrollementDto(
                        enrollment.getId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getStatus());

        return responseEnrollementDto;
    }

    public List<ResponseEnrollementDto> getListEnrollment(){
        List<Enrollment> listEnrollment = enrollmentRepository.findAll();
        List<ResponseEnrollementDto> listEnrollmentDto = new ArrayList<>();
        for (Enrollment enrollment: listEnrollment) {
            ResponseEnrollementDto dto =
                    new ResponseEnrollementDto(
                            enrollment.getId(),
                            enrollment.getEnrollmentDate(),
                            enrollment.getStatus());
                    listEnrollmentDto.add(dto);
        }
        return listEnrollmentDto;
    }

    public ResponseEnrollementDto getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada!"));
        return toConverteEnrollmentParaResponseEnrollmentDto(enrollment);
    }

    public byte[] exportStudentsToCsv(List<Students> studentsList) throws IOException {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Id; Nome; Data de Nascimento; Grade; Nome do Responsável; Endereço; Número de Contato\n");

        for (Students student : studentsList) {
            csvContent.append(student.getId()).append(";")
                    .append(student.getName()).append(";")
                    .append(student.getBirthday() != null ? student.getBirthday() : "").append(";")
                    .append(student.getGrade()).append(";")
                    .append(student.getParent_name()).append(";")
                    .append(student.getAddress()).append(";")
                    .append(student.getContact_number()).append("\n");
        }

        return csvContent.toString().getBytes(StandardCharsets.UTF_8);
    }

    public List<Students> getListStudents() {
        return studentsRepository.findAll();
    }


}

