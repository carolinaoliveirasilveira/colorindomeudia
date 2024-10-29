package com.colorindomeudia.colorindomeudia.service;

import com.colorindomeudia.colorindomeudia.model.Classes;
import com.colorindomeudia.colorindomeudia.model.dto.RequestClassesDto;
import com.colorindomeudia.colorindomeudia.model.dto.ResponseClassesDto;
import com.colorindomeudia.colorindomeudia.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassesService {
    @Autowired(required = true)
    ClassesRepository classesRepository;

    public ResponseClassesDto createClasses(RequestClassesDto requestClassesDto) {
        Classes classes = toClassesDto(requestClassesDto);
        Classes returnClasses = classesRepository.save(classes);
        ResponseClassesDto responseClassesDto = toConverteClassesParaResponseClassesDto(returnClasses);
        return responseClassesDto;
    }

    private ResponseClassesDto toConverteClassesParaResponseClassesDto(Classes classes) {
        ResponseClassesDto responseClassesDto =
                new ResponseClassesDto(
                        classes.getId(),
                        classes.getLevel(),
                        classes.getTeacher(),
                        classes.getSession(),
                        classes.getDescription());

        return responseClassesDto;

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
        Optional<Classes> classes = classesRepository.findById(id);
        return toConverteOptionalClassesParaResponseClassesDto(classes);
    }

    private ResponseClassesDto toConverteOptionalClassesParaResponseClassesDto(Optional<Classes> classes) {
        return new ResponseClassesDto(
                classes.get().getId(),
                classes.get().getLevel(),
                classes.get().getTeacher(),
                classes.get().getSession(),
                classes.get().getDescription());
    }


    public List<ResponseClassesDto> getListClasses() {
        List<Classes> listClasses = classesRepository.findAll();
        List<ResponseClassesDto> listResponseClassesDto = new ArrayList<>();
        for (Classes classes: listClasses) {
            ResponseClassesDto dto =
                    new ResponseClassesDto(
                            classes.getId(),
                            classes.getLevel(),
                            classes.getTeacher(),
                            classes.getSession(),
                            classes.getDescription());
            listResponseClassesDto.add(dto);
        }
        return listResponseClassesDto;
    }
}
