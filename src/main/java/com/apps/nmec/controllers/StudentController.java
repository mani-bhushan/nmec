package com.apps.nmec.controllers;

import com.apps.nmec.mappers.StudentMapper;
import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import com.apps.nmec.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    StudentMapper studentMapper;

    @Operation(summary = "add candidate")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELLOR','STAFF')")
    public StudentResponse addStudent(@RequestBody @Valid StudentRequest studentRequest){
        return studentService.addStudent(studentRequest);
    }

    @Operation(summary = "get all candidate")
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELLOR','STAFF')")
    public List<StudentResponse> getStudents(){
        return studentService.getStudents();
    }

    @Operation(summary = "get candidate details")
    @GetMapping("/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELLOR','STAFF','USER','CANDIDATE')")
    public List<StudentResponse> getStudentDetails(@PathVariable String studentId){
        return studentService.getStudents();
    }

}
