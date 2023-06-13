package com.apps.nmec.requests;

import com.apps.nmec.entities.AcademicDetails;
import com.apps.nmec.entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {

    @NotNull
    String rollNo;

    @NotNull
    LocalDateTime admissionDate;

    @NotNull
    String receiptNo;

    @NotNull
    String counselorId;

    @NotNull
    String faculty;

    @NotNull
    String session;

    @NotNull
    String majorSubjects;

    @NotNull
    String irc;

    @NotNull
    String language;

    @NotNull
    String name;

    @NotNull
    String gender;

    @NotNull
    String fatherName;

    @NotNull
    String motherName;

    @NotNull
    LocalDateTime dateOfBirth;

    @NotNull
    String aadharNo;

    @NotNull
    String mobileNo;

    @NotNull
    String religion;

    @NotNull
    String emailId;

    @NotNull
    String category;

    @NotNull
    String caste;

    @NotNull
    AddressEntity presentAddress;

    @NotNull
    AddressEntity permanentAddress;

    @NotNull
    String maritalStatus;

    @NotNull
    List<AcademicDetails> academicDetails;

    @NotNull
    String nationality;

}
