package com.apps.nmec.controllers;

import com.apps.nmec.mappers.StudentMapper;
import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import com.apps.nmec.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    StudentMapper studentMapper;

    @PostMapping("/add")
    public StudentResponse addStudent(@RequestBody @Valid StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/all")
    public List<StudentResponse> getStudents(){
        return studentService.getStudents();
    }



}