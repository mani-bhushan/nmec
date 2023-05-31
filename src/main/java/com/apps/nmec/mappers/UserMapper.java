package com.apps.nmec.mappers;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    public UserModel mapUserEntityToNewModel(final UserEntity userEntity) {
        final UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setName(userEntity.getName());
        userModel.setEmail(userEntity.getEmail());
        userModel.setRoles(userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet()));
        userModel.setStartDate(userEntity.getStartDate());
        userModel.setEndDate(userEntity.getEndDate());
        userModel.setActiveUser(userEntity.getActiveUser());
        return userModel;
    }

    public UserEntity mapUserModelToNewEntity(final UserModel userModel) {

        final UserEntity userEntity = new UserEntity();
        userEntity.setName(userModel.getName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userEntity.setRoles(new HashSet<RoleEntity>());
        userEntity.setStartDate(LocalDateTime.now());
        userEntity.setEndDate(LocalDateTime.now().plusDays(10));
        userEntity.setActiveUser(userModel.getActiveUser());
        return userEntity;
    }

    //Test Role -> USER, ADMIN, STAFF
    public UserEntity mapRolesToUserEntity(UserEntity userEntity, Set<RoleEntity> roleEntities) {
        userEntity.setRoles(roleEntities);
        return userEntity;
    }
}
