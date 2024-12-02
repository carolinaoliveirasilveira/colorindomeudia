package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Classes;
import com.colorindomeudia.colorindomeudia.model.PreEnrollment;
import com.colorindomeudia.colorindomeudia.model.Students;
import com.colorindomeudia.colorindomeudia.model.dto.PreEnrollmentDto;
import com.colorindomeudia.colorindomeudia.model.dto.RequestPreEnrollmentDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponsePreEnrollmentDto;
import com.colorindomeudia.colorindomeudia.repository.ClassesRepository;
import com.colorindomeudia.colorindomeudia.repository.PreEnrollmentRepository;
import com.colorindomeudia.colorindomeudia.repository.StudentsRepository;
import com.colorindomeudia.colorindomeudia.util.StatusPay;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PreEnrollmentService {
    @Autowired
    PreEnrollmentRepository preEnrollmentRepository;

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    ClassesRepository classesRepository;

    public ResponsePreEnrollmentDto createPreEnrollment(RequestPreEnrollmentDto requestPreEnrollmentDto) {
        PreEnrollment preEnrollment = toPreEnrollmentDto(requestPreEnrollmentDto);
        PreEnrollment returnPreEnrollment = preEnrollmentRepository.save(preEnrollment);
        return toConvertePreEnrollmentEmPreEnrollmentDto(returnPreEnrollment);
    }

    private ResponsePreEnrollmentDto toConvertePreEnrollmentEmPreEnrollmentDto(PreEnrollment preEnrollment) {
        return new ResponsePreEnrollmentDto(
                preEnrollment.getPreEnrollmentId(),
                preEnrollment.getPreEnrollmentDate(),
                preEnrollment.getStatus().toString(),
                preEnrollment.getStudentName(),
                preEnrollment.getStudentEmail(),
                preEnrollment.getStudentPhone());
    }

    // Conversão de RequestPreEnrollmentDto para PreEnrollment
    private PreEnrollment toPreEnrollmentDto(RequestPreEnrollmentDto requestPreEnrollmentDto) {
        PreEnrollment preEnrollment = new PreEnrollment();
        preEnrollment.setPreEnrollmentDate(requestPreEnrollmentDto.preEnrollmentDate());
        preEnrollment.setStatus(StatusPay.valueOf(requestPreEnrollmentDto.status()));
        preEnrollment.setStudentName(requestPreEnrollmentDto.studentName());
        preEnrollment.setStudentEmail(requestPreEnrollmentDto.studentEmail());
        preEnrollment.setStudentPhone(requestPreEnrollmentDto.studentPhone());

        // Encontrar o aluno
        var idStudent = requestPreEnrollmentDto.studentId();
        Students student = studentsRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não existe!"));
        preEnrollment.setStudents(student);

        // Encontrar a classe desejada
        var idClass = requestPreEnrollmentDto.classesId();
        Classes classes = classesRepository.findById(idClass)
                .orElseThrow(() -> new EntityNotFoundException("Classe não existe!"));
        preEnrollment.setDesiredClass(classes);

        return preEnrollment;
    }

      public List<PreEnrollmentDto> getPreEnrollmentStudents(Long desiredClassId, String status) {
        List<PreEnrollment> preEnrollmentList = preEnrollmentRepository.findByDesiredClassId(desiredClassId);
          return preEnrollmentList.stream()
                  .filter(e -> e.getStatus().name().equals(status))
                  .map(preEnrollment -> new PreEnrollmentDto(
                          preEnrollment.getStudents().getId(),
                          preEnrollment.getStudents().getName(),
                          preEnrollment.getStatus().toString()
                  ))
                .collect(Collectors.toList());
      }


    public ResponsePreEnrollmentDto getPreEnrollmentById(Long preEnrollmentId) {
        Optional<PreEnrollment> preEnrollmentOptional = preEnrollmentRepository.findById(preEnrollmentId);
        return preEnrollmentOptional
                .map(this::toConvertePreEnrollmentEmPreEnrollmentDto)
                .orElseThrow(() -> new EntityNotFoundException("Pré-inscrição não encontrada!"));
    }

}