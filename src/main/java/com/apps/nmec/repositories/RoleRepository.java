package com.apps.nmec.repositories;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findByRole(ERole eRole);

    Set<RoleEntity> findByRoleIn(Set<ERole> role);
}
