package com.apps.nmec.mappers;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired private PasswordEncoder passwordEncoder;
    public List<UserModel> mapUserEntityListToModelList(final List<UserEntity> userEntityList) {
        final List<UserModel> userModelList = new ArrayList<>();
        userEntityList.forEach( userEntity ->  {
            final UserModel userModel = new UserModel();
            userModel.setId(userEntity.getId());
            userModel.setName(userEntity.getName());
            userModel.setEmail(userEntity.getEmail());
            userModel.setPassword(userEntity.getPassword());
            userModel.setRoles(userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet()));
            // userEntity.getRoles().forEach( role -> { userModel.getRoles().add(role.getRole()); });
            userModelList.add(userModel);
        });
        return userModelList;
    }

    public UserModel mapUserEntityToModel(final UserEntity userEntity) {
        final UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setName(userEntity.getName());
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setRoles(userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet()));
        // userEntity.getRoles().forEach( role -> { userModel.getRoles().add(role.getName()); });
        return userModel;
    }

    public UserEntity mapUserModelToEntity(final UserModel userModel, final Set<RoleEntity> roles) {

        final UserEntity userEntity = new UserEntity();
        userEntity.setName(userModel.getName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userEntity.setRoles(roles);
        return userEntity;
    }
}
