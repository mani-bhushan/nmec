package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user")
public class UserEntity extends BaseUserEntity implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 4926468583005150701L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	@Column(name = "name", nullable = false)
	private String name;

	@JsonProperty("email")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@JsonProperty("password")
	@Column(name = "password", nullable = false)
	private String password;

	@Builder.Default
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "roles_id")
	)
	private Set<RoleEntity> roles = new HashSet<>();

	public void addRole(RoleEntity roleEntity) {
		this.roles.add(roleEntity);
	}


	/**
	 * Returns the authorities granted to the user. Cannot return <code>null</coe>.
	 *
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
		}
		return authorities;
	}

	/**
	 * Returns the username used to authenticate the user. Cannot return
	 * <code>null</code>.
	 *
	 * @return the username (never <code>null</code>)
	 */
	@Override
	public String getUsername() {
		return this.email;
	}

	/**
	 * Indicates whether the user's account has expired. An expired account cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user's account is valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 *
	 * @return <code>true</code> if the user's credentials are valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
