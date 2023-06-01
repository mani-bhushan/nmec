package com.apps.nmec.repositories;

import com.apps.nmec.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {

}

