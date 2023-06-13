package com.apps.nmec.mappers;

import com.apps.nmec.entities.StudentEntity;
import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentEntity mapRequestToEntity(StudentRequest studentRequest);

    StudentResponse mapEntityToResponse(StudentEntity studentEntity);
}
