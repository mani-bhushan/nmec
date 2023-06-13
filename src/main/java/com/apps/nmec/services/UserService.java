package com.apps.nmec.services;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.enums.ERole;
import com.apps.nmec.exceptionhandlers.CustomException;
import com.apps.nmec.mappers.UserMapper;
import com.apps.nmec.models.UserModel;
import com.apps.nmec.repositories.RoleRepository;
import com.apps.nmec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    @Autowired private RoleRepository roleRepository;

    @Autowired private UserMapper userMapper;

    public UserModel createUser(final UserModel userModel) {
        final UserEntity userEntity = userMapper.mapUserModelToNewEntity(userModel);
        userEntity.setRoles(roleRepository.findByRoleIn(userModel.getRoles().stream()
                .filter(e -> { return e != ERole.ADMIN; })
                .collect(Collectors.toSet())));
        if (!userModel.getRoles().contains(ERole.USER)) userEntity.addRole(roleRepository.findByRole(ERole.USER));
        userRepository.saveAndFlush(userEntity);
        return userMapper.mapUserEntityToNewModel(userEntity);
    }

    public UserModel updateUserRole(final UserModel userModel) {

        Optional.ofNullable(userModel.getRoles()).filter( y -> !y.isEmpty())
                .orElseThrow(() -> CustomException.builder().errorMessage("Please provide Roles").httpStatus(HttpStatus.EXPECTATION_FAILED).build());

        UserEntity userEntity = userRepository.findByEmail(userModel.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User " + userModel.getEmail() + " not found."));

        final Set<ERole> userRoles = userModel.getRoles();
        final Set<RoleEntity> roleEntities = roleRepository.findByRoleIn(userRoles);

        Optional.ofNullable(roleEntities).filter( y -> !y.isEmpty())
                .orElseThrow(() -> CustomException.builder().errorMessage("Role not found").httpStatus(HttpStatus.BAD_REQUEST).build());

        userEntity = userRepository.saveAndFlush(userMapper.mapRolesToUserEntity(userEntity, roleEntities));
        return userMapper.mapUserEntityToNewModel(userEntity);
    }

//    public static void main(String[] args) {
//        Set e = new HashSet<>();
//        Set p = Optional.ofNullable(e).filter(y -> !y.isEmpty()).orElseThrow(() -> new UsernameNotFoundException("Roles not found"));
//        System.out.println("Completed..." + p);
//    }
}
