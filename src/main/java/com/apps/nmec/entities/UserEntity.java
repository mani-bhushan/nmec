package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 4926468583005150701L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
	@JsonProperty("id")
	private String id;
	
	@Column
	@JsonProperty("name")
	private String name;
	
	@Column
	@JsonProperty("email")
	private String email;
	
	@Column
	@JsonIgnore
	@JsonProperty("password")
	private String password;

}
