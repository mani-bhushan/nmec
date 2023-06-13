package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    @JsonProperty("id")
    String id;

    private String rollNo;

    private LocalDateTime admissionDate;

    private String receiptNo;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsellor_id", referencedColumnName = "id")
    private UserEntity counsellor;

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

    @OneToOne
    private AddressEntity presentAddress;

    @OneToOne
    private AddressEntity permanentAddress;

    private String maritalStatus;

    @OneToMany
    private Set<AcademicDetails> academicDetails;

    private String nationality;

}
