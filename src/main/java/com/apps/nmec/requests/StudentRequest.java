package com.apps.nmec.requests;

import com.apps.nmec.entities.AcademicDetails;
import com.apps.nmec.entities.AddressEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentRequest {

    String rollNo;

    LocalDateTime admissionDate;

    String receiptNo;

    String counselorId;

    String faculty;

    String session;

    String majorSubjects;

    String irc;

    String language;

    String name;

    String gender;

    String fatherName;

    String motherName;

    LocalDateTime dateOfBirth;

    String aadharNo;

    String mobileNo;

    String religion;

    String emailId;

    String category;

    String caste;

    AddressEntity presentAddress;

    AddressEntity permanentAddress;

    String maritalStatus;

    List<AcademicDetails> academicDetails;

    String nationality;

}
