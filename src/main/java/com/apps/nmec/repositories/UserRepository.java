package com.apps.nmec.repositories;

import com.apps.nmec.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	// UserEntity findByName(String name);
	
	// UserEntity findByEmail(String email);

	Optional<UserEntity> findByEmail(String email);

}