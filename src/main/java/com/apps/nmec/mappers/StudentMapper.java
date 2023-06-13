package com.apps.nmec.mappers;

import com.apps.nmec.entities.StudentEntity;
import com.apps.nmec.requests.StudentRequest;
import com.apps.nmec.responses.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    //@Mapping(target="counsellor", ignore = true)
    //@Mapping(target="initiatedBy", ignore = true)
    //@Mapping(target="updatedBy", ignore = true)
    //@Mapping(target="initiatedOn", ignore = true)
    //@Mapping(target="updatedOn", ignore = true)

    //@Mapping(target="startDate", ignore = true)
    //@Mapping(target="endDate", ignore = true)
    @Mapping(target = "aadharNo",source = "studentRequest.aadharNo")
    StudentEntity mapRequestToEntity(StudentRequest studentRequest);

    //@Mapping(target="counsellorId", ignore = true)
    StudentResponse mapEntityToResponse(StudentEntity studentEntity);
}
