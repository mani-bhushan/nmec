package com.apps.nmec.services;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.enums.ERole;
import com.apps.nmec.mappers.UserMapper;
import com.apps.nmec.models.UserModel;
import com.apps.nmec.repositories.RoleRepository;
import com.apps.nmec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    @Autowired private RoleRepository roleRepository;

    @Autowired private UserMapper userMapper;


    public List<UserModel> getAllUsers() {
        return userMapper.mapUserEntityListToModelList(userRepository.findAll());
    }

    public UserModel createUser(final UserModel userModel) {

        final Set<ERole> userRoles = userModel.getRoles();
        final Set<RoleEntity> roleEntities = roleRepository.findByRoleIn(userRoles);
        if (userRoles.isEmpty() || roleEntities.isEmpty()) {
            roleEntities.add(RoleEntity.builder().role(ERole.USER).build());
        }

        final UserEntity userEntity = userRepository.saveAndFlush(userMapper.mapUserModelToEntity(userModel, roleEntities));
        return userMapper.mapUserEntityToModel(userEntity);
    }
}
