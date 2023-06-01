package com.apps.nmec.repositories;

import com.apps.nmec.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, String> {

}