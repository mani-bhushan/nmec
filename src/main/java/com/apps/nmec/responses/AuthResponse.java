package com.apps.nmec.responses;

import com.apps.nmec.enums.ERole;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AuthResponse {

	private String email;
	private Set<ERole> roles;
	private String accessToken;

}
