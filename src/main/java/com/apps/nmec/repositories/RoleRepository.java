package com.apps.nmec.repositories;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findByRole(ERole eRole);

    Set<RoleEntity> findByRoleIn(Set<ERole> role);
}
