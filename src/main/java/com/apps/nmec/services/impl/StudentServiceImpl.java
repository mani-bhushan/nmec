package com.apps.nmec.services.impl;

import com.apps.nmec.entities.StudentEntity;
import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.enums.ERole;
import com.apps.nmec.mappers.StudentMapper;
import com.apps.nmec.mappers.UserMapper;
import com.apps.nmec.repositories.RoleRepository;
import com.apps.nmec.repositories.StudentRepository;
import com.apps.nmec.repositories.UserRepository;
import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import com.apps.nmec.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final StudentMapper studentMapper;

    private final UserMapper userMapper;

    public StudentResponse addStudent(final StudentRequest studentRequest){
        final UserEntity counsellor = userRepository.findById(studentRequest.getCounsellorId())
                .orElseThrow(() -> new UsernameNotFoundException("Counsellor " + studentRequest.getCounsellorId() + " not found."));
        final UserEntity userEntity = userMapper.mapStudentRequestToUserEntity(studentRequest);
        userEntity.addRole(roleRepository.findByRole(ERole.CANDIDATE));
        userEntity.addRole(roleRepository.findByRole(ERole.USER));
        userRepository.save(userEntity);
        studentRequest.setUser(userEntity);
        final StudentEntity studentEntity = studentMapper.mapRequestToEntity(studentRequest);
        studentEntity.setCounsellor(counsellor);
        studentRepository.save(studentEntity);
        return studentMapper.mapEntityToResponse(studentEntity);
    }

    public List<StudentResponse> getStudents(){
        return studentRepository.findAll().stream().map(studentMapper::mapEntityToResponse).collect(Collectors.toList());
    }
}
