package com.apps.nmec.models;

import com.apps.nmec.enums.ERole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserModel implements Serializable {

	private static final long serialVersionUID = 4926468583005150702L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("id") private String id;

	@NonNull
	@JsonProperty("name") private String name;

	@Email(message = "Please provide valid email.")
	@NonNull
	@JsonProperty("email") private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Size(min = 3, max = 40,message = "Password must be between 3 to 40 chars")
	@NonNull
	@JsonProperty("password") private String password;

	@JsonProperty("startDate") private LocalDateTime startDate;

	@JsonProperty("endDate") private LocalDateTime endDate;

	@JsonProperty("activeUser") private Boolean activeUser;

	@JsonProperty("contactNo")
	@NonNull
	private String contactNo;

	@JsonProperty("roles")
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private Set<ERole> roles = new HashSet<>();

}
