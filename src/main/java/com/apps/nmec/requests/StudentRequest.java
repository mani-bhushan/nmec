package com.apps.nmec.requests;

import com.apps.nmec.entities.AcademicDetails;
import com.apps.nmec.entities.AddressEntity;
import com.apps.nmec.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest implements Serializable {

    @NotNull
    private String rollNo;

    @NotNull
    private LocalDateTime admissionDate;

    @NotNull
    private String receiptNo;

    @NotNull
    private String counsellorId;

    @NotNull
    private String faculty;

    @NotNull
    private String session;

    @NotNull
    private String majorSubjects;

    @NotNull
    private String irc;

    @NotNull
    private String language;

    @NotNull
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private String fatherName;

    @NotNull
    private String motherName;

    @NotNull
    private LocalDateTime dateOfBirth;

    @NotNull
    private String aadharNo;

    @NotNull
    private String mobileNo;

    @NotNull
    private String religion;

    @NotNull
    private String emailId;

    @NotNull
    private String category;

    @NotNull
    private String caste;

    @NotNull
    private AddressEntity presentAddress;

    @NotNull
    private AddressEntity permanentAddress;

    @NotNull
    private String maritalStatus;

    @NotNull
    private Set<AcademicDetails> academicDetails;

    @NotNull
    private String nationality;

    @JsonIgnore
    private UserEntity user;

}
