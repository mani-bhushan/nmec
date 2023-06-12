package com.apps.nmec.services.impl;

import com.apps.nmec.entities.StudentEntity;
import com.apps.nmec.mappers.StudentMapper;
import com.apps.nmec.repositories.StudentRepository;
import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import com.apps.nmec.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentResponse saveStudent(StudentRequest studentRequest){
        return studentMapper.mapEntityToResponse(studentRepository.save(studentMapper.mapRequestToEntity(studentRequest)));
    }

    public List<StudentResponse> getStudents(){
        return studentRepository.findAll().stream().map(studentMapper::mapEntityToResponse).collect(Collectors.toList());
    }
}
