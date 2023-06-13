package com.apps.nmec.repositories;

import com.apps.nmec.entities.AddressEntity;
import com.apps.nmec.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {

}

