package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Teachers;
import com.colorindomeudia.colorindomeudia.model.dto.RequestTeachersDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseTeachersDto;
import com.colorindomeudia.colorindomeudia.repository.TeachersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeachersService {
    @Autowired
    TeachersRepository teachersRepository;

    public List<Teachers> listTeachers() {
        return teachersRepository.findAll();
    }

    public ResponseTeachersDto createTeacher(RequestTeachersDto requestTeachersDto) {
        var byName = teachersRepository.findByName(requestTeachersDto.name());
        if (!byName.isEmpty()) {
            throw new RuntimeException("Professor já cadastrado no banco de dados!");
        }
        Teachers teachers = toTeacherDto(requestTeachersDto);

        Teachers returnTeacher = teachersRepository.save(teachers);
        ResponseTeachersDto responseTeachersDto = toConverteTeacherParaResponseTeacherDto(returnTeacher);
        return responseTeachersDto;

}
    private ResponseTeachersDto toConverteTeacherParaResponseTeacherDto(Teachers teachers) {
        ResponseTeachersDto responseTeachersDto =
                new ResponseTeachersDto(
                        teachers.getId(),
                        teachers.getName(),
                        teachers.getDiscipline());

        return responseTeachersDto;
    }

    private Teachers toTeacherDto(RequestTeachersDto requestTeachersDto) {
        Teachers teachers = new Teachers();
        teachers.setName(requestTeachersDto.name());
        teachers.setDiscipline(requestTeachersDto.discipline());
        return teachers;

    }

    public List<ResponseTeachersDto> getListTeachers() {
        List<Teachers> listTeachers = teachersRepository.findAll();
        List<ResponseTeachersDto> listTeachersDto = new ArrayList<>();
        for (Teachers teachers: listTeachers) {
            ResponseTeachersDto dto =
                    new ResponseTeachersDto(
                            teachers.getId(),
                            teachers.getName(),
                            teachers.getDiscipline());
            listTeachersDto.add(dto);
        }

        return listTeachersDto;
    }

   public ResponseTeachersDto getTeachersById(Long id) {
        Teachers teachers = teachersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrato!"));
        return toConverteTeacherParaResponseTeacherDto(teachers);
   }






}

