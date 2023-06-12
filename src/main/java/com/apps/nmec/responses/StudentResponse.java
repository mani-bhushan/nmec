package com.apps.nmec.responses;

import com.apps.nmec.entities.AcademicDetails;
import com.apps.nmec.entities.AddressEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentResponse {

    private String rollNo;

    private LocalDateTime admissionDate;

    private String receiptNo;

    private String counselorId;

    private String faculty;

    private String session;

    private String majorSubjects;

    private String irc;

    private String language;

    private String name;

    private String gender;

    private String fatherName;

    private String motherName;

    private LocalDateTime dateOfBirth;

    private String aadharNo;

    private String mobileNo;

    private String religion;

    private String emailId;

    private String category;

    private String caste;

    private AddressEntity presentAddress;

    private AddressEntity permanentAddress;

    private String maritalStatus;

    private List<AcademicDetails> academicDetails;

    private String nationality;

}
