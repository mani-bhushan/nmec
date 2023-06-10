package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper=true)
@Data
public class StudentEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    @JsonProperty("id")
    String id;

    String rollNo;

    LocalDateTime admissionDate;

    String receiptNo;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id", referencedColumnName = "id")
    UserEntity counselor;

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

    @OneToOne
    AddressEntity presentAddress;

    @OneToOne
    AddressEntity permanentAddress;

    String maritalStatus;

    @OneToMany
    List<AcademicDetails> academicDetails;

    String nationality;

}
