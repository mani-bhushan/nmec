package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(callSuper=true)
@Data
public class AcademicDetails extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    @JsonProperty("id")
    String id;

    String exam;

    String faculty;

    String universityName;

    String collegeName;

    String passingYear;

    String subjectNames;

    Integer fullMarks;

    Integer obtainedMarks;

    String division;

    @ManyToOne
    @JoinColumn(name="student_id")
    StudentEntity student;
}
