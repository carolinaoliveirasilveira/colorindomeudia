package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Classes;
import com.colorindomeudia.colorindomeudia.model.Enrollment;
import com.colorindomeudia.colorindomeudia.model.Students;
import com.colorindomeudia.colorindomeudia.model.dto.RequestClassesDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseClassesDto;
import com.colorindomeudia.colorindomeudia.repository.ClassesRepository;
import com.colorindomeudia.colorindomeudia.repository.EnrollmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClassesService {
    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public ResponseClassesDto createClasses(RequestClassesDto requestClassesDto) {
        Classes classes = toClassesDto(requestClassesDto);
        Classes returnClasses = classesRepository.save(classes);
        return toConverteClassesParaResponseClassesDto(returnClasses);
    }

    private ResponseClassesDto toConverteClassesParaResponseClassesDto(Classes classes) {
        return new ResponseClassesDto(
                classes.getId(),
                classes.getLevel(),
                classes.getTeacher(),
                classes.getSession(),
                classes.getDescription());
    }

    public List<Students> getStudentsNames(Long classId) {
        Classes classes = classesRepository.findListingStudentsInClass(classId);
        List<Students> studentNames = new ArrayList<>();
        return  classes.getEnrollments().stream()
              //  .filter(s -> s.getStudents().getAge() >= 2)
                .map(Enrollment::getStudents).toList();
    }


    private Classes toClassesDto(RequestClassesDto requestClassesDto) {
        Classes classes = new Classes();
        classes.setLevel(requestClassesDto.level());
        classes.setTeacher(requestClassesDto.teacher());
        classes.setSession(requestClassesDto.session());
        classes.setDescription(requestClassesDto.description());
        return classes;
    }

    public ResponseClassesDto getClassById(Long id) {
        Optional<Classes> optionalClasses = classesRepository.findById(id);
        return optionalClasses.map(this::toConverteClassesParaResponseClassesDto)
                .orElseThrow(() -> new EntityNotFoundException("Classe não encontrada!"));
    }

    public List<ResponseClassesDto> getListClasses() {
        List<Classes> listClasses = classesRepository.findAll();
        List<ResponseClassesDto> listResponseClassesDto = new ArrayList<>();
        for (Classes classes : listClasses) {
            listResponseClassesDto.add(toConverteClassesParaResponseClassesDto(classes));
        }
        return listResponseClassesDto;
    }
    public List<Students> getBirthdayStudents(Long classId, int month) {
        Classes classes = classesRepository.findListingStudentsInClass(classId);
                return classes.getEnrollments().stream()
                .map(Enrollment::getStudents)
               .filter(student -> student.getBirthday() != null && isBirthdayInMonth(student.getBirthday(), month))
                .collect(Collectors.toList());
    }

    private boolean isBirthdayInMonth(Date birthday, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        return calendar.get(Calendar.MONTH) + 1 == month;
    }

}