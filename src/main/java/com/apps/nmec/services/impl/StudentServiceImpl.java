package com.apps.nmec.services.impl;

import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.mappers.StudentMapper;
import com.apps.nmec.mappers.UserMapper;
import com.apps.nmec.repositories.StudentRepository;
import com.apps.nmec.repositories.UserRepository;
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

    private final UserRepository userRepository;

    private final StudentMapper studentMapper;

    private final UserMapper userMapper;

    public StudentResponse addStudent(final StudentRequest studentRequest){
        final UserEntity userEntity = userMapper.mapStudentRequestToUserEntity(studentRequest);
        userRepository.saveAndFlush(userEntity);
        return studentMapper.mapEntityToResponse(studentRepository.save(studentMapper.mapRequestToEntity(studentRequest)));
    }

    public List<StudentResponse> getStudents(){
        return studentRepository.findAll().stream().map(studentMapper::mapEntityToResponse).collect(Collectors.toList());
    }
}
