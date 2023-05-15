package com.apps.nmec.mappers;

import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.models.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public List<UserModel> mapUserEntityToModel(final List<UserEntity> userEntityList) {
        final List<UserModel> userModelList = new ArrayList<>();
        userEntityList.forEach( userEntity ->  {
            final UserModel userModel = new UserModel();
            userModel.setId(userEntity.getId());
            userModel.setName(userEntity.getName());
            userModel.setEmail(userEntity.getEmail());
            userModel.setPassword(userEntity.getPassword());

            userModelList.add(userModel);
        });
        return userModelList;
    }

    public List<UserEntity> mapUserModelToEntity(final List<UserModel> userModelList) {
        final List<UserEntity> userEntityList = new ArrayList<>();
        userModelList.forEach( userModel ->  {
            final UserEntity userEntity = new UserEntity();
            userEntity.setName(userModel.getName());
            userEntity.setEmail(userModel.getEmail());
            userEntity.setPassword(userModel.getPassword());

            userEntityList.add(userEntity);
        });
        return userEntityList;
    }
}
