package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="country")
public class CountryEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
	@JsonProperty("id")
	String id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "country",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StateEntity> states;

}










