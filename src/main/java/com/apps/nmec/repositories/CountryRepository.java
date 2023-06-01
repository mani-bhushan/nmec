package com.apps.nmec.repositories;

import com.apps.nmec.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {

}