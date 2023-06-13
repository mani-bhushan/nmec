package com.apps.nmec.services;

import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {

    StudentResponse addStudent(final StudentRequest studentRequest);

    public List<StudentResponse> getStudents();

}
