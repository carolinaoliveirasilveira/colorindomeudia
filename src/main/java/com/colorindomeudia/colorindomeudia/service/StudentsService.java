package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Students;
import com.colorindomeudia.colorindomeudia.model.dto.RequestStudentDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseStudentDto;
import com.colorindomeudia.colorindomeudia.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired(required = true)
    private StudentsRepository studentsRepository;

    public ResponseStudentDto createStudent(RequestStudentDto requestStudentDto) {
        var byName = studentsRepository.findByName(requestStudentDto.name());
        if (!byName.isEmpty()) {
            throw new RuntimeException("Aluno já cadastrado no banco de dados!");
        }
        Students students = toStudentDto(requestStudentDto);

        Students returnStudents = studentsRepository.save(students);
        ResponseStudentDto responseStudentDto = toConverteEstudanteParaResponseStudentDto(returnStudents);
        return responseStudentDto;
    }

    private ResponseStudentDto toConverteEstudanteParaResponseStudentDto(Students students) {
        ResponseStudentDto responseStudentDto =
                new ResponseStudentDto(
                        students.getId(),
                        students.getName(),
                        students.getAge(),
                        students.getGrade(),
                        students.getParent_name(),
                        students.getAddress(),
                        students.getContact_number());
        return responseStudentDto;
    }

    private Students toStudentDto(RequestStudentDto requestStudentDto) {
        Students students = new Students();
        students.setName(requestStudentDto.name());
        students.setAge(requestStudentDto.age());
        students.setAddress(requestStudentDto.address());
        students.setGrade(requestStudentDto.grade());
        students.setContact_number(requestStudentDto.contact_number());
        students.setParent_name(requestStudentDto.parent_name());
        return students;
    }

    public List<ResponseStudentDto> getListStudents() {
        List<Students> listStudents = studentsRepository.findAll();

        List<ResponseStudentDto> listResponseStudentsDto = new ArrayList<>();
        for (Students students: listStudents) {
            ResponseStudentDto dto = new ResponseStudentDto(
                    students.getId(),
                    students.getName(),
                    students.getAge(),
                    students.getGrade(),
                    students.getParent_name(),
                    students.getAddress(),
                    students.getContact_number());
            listResponseStudentsDto.add(dto);
        }
        return listResponseStudentsDto;
    }

    public ResponseStudentDto getStudentById(Long id) {
        Optional<Students> student = studentsRepository.findById(id);
        return toConverteOptionalStudentParaResponseStudentDto(student);


    }

    private ResponseStudentDto toConverteOptionalStudentParaResponseStudentDto(Optional<Students> student) {

               return new ResponseStudentDto(
                        student.get().getId(),
                        student.get().getName(),
                        student.get().getAge(),
                        student.get().getGrade(),
                        student.get().getParent_name(),
                        student.get().getAddress(),
                        student.get().getContact_number());

    }

}
