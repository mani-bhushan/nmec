package com.apps.nmec.models;

import com.apps.nmec.enums.ERole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements Serializable {

	private static final long serialVersionUID = 4926468583005150702L;

	@JsonProperty("id") private String id;

	@JsonProperty("name") private String name;

	@JsonProperty("email") private String email;

	@JsonProperty("password") private String password;

	@JsonProperty("startDate") private LocalDateTime startDate;

	@JsonProperty("endDate") private LocalDateTime endDate;

	@JsonProperty("activeUser") private Boolean activeUser;

	@JsonProperty("roles")
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private Set<ERole> roles = new HashSet<>();

}
