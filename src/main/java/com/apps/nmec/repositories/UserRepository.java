package com.apps.nmec.repositories;

import com.apps.nmec.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByName(String name);
	
	UserEntity findByEmail(String email);

}