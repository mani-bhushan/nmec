package com.apps.nmec.entities;

import com.apps.nmec.enums.ERole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(255)", nullable = false)
	@JsonProperty("id")
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 50, unique = true)
	private ERole role;

	@Override
	public String toString() { return this.role.name(); }
}
