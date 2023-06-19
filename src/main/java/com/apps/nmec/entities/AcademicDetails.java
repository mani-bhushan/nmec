package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="academic_details")
public class AcademicDetails extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    @JsonProperty("id")
    private String id;

    @NotNull
    private String exam;

    @NotNull
    private String faculty;

    @NotNull
    private String universityName;

    @NotNull
    private String collegeName;

    @NotNull
    private String passingYear;

    @NotNull
    private String subjectNames;

    @NotNull
    private Integer fullMarks;

    @NotNull
    private Integer obtainedMarks;

    @NotNull
    private String division;

}
