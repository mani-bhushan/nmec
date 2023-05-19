package com.apps.nmec;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.enums.ERole;
import com.apps.nmec.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class NmecApplication {

	public static void main(String[] args) {
		SpringApplication.run(NmecApplication.class, args);
	}

	@Bean
	CommandLineRunner init (final RoleRepository roleRepository){
		return args -> {
			final List<String> eRoles = Stream.of(ERole.values()).map(Enum::name).collect(Collectors.toList());
			final List<String> existingRoles = roleRepository.findAll().stream().map(e-> e.getRole().name()).collect(Collectors.toList());
			eRoles.removeAll(existingRoles);
			eRoles.forEach(name -> roleRepository.save(RoleEntity.builder().role(ERole.valueOf(name)).build()));
			final List<String> eRoleList = Stream.of(ERole.values()).map(Enum::name).collect(Collectors.toList());
			existingRoles.removeAll(eRoleList);
			existingRoles.forEach(name -> roleRepository.delete(roleRepository.findByRole(ERole.valueOf(name))));
		};
	}

}
