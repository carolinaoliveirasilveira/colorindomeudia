package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Classes;
import com.colorindomeudia.colorindomeudia.model.dto.ClassesDto;
import com.colorindomeudia.colorindomeudia.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesService {
    @Autowired(required = true)
    ClassesRepository classesRepository;

    public String createClasses(ClassesDto classesDto) {
        Classes classes = toClassesDto(classesDto);
        return classesRepository.save(classes).toString();
    }

    private Classes toClassesDto(ClassesDto classesDto) {
        Classes classes = new Classes();
        classes.setLevel(classesDto.level());
        classes.setTeacher(classesDto.teacher());
        classes.setSession(classesDto.session());
        classes.setDescription(classesDto.description());
        return classes;

    }

}
