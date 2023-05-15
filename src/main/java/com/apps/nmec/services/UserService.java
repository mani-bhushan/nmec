package com.apps.nmec.services;

import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.mappers.UserMapper;
import com.apps.nmec.models.UserModel;
import com.apps.nmec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public List<UserModel> getAllUsers() {
        return userMapper.mapUserEntityToModel(userRepository.findAll());
    }

    public List<UserModel> createUsers(final List<UserModel> userModelList) {
        final List<UserEntity> userEntityList = userRepository.saveAllAndFlush(userMapper.mapUserModelToEntity(userModelList));
        return userMapper.mapUserEntityToModel(userEntityList);
    }
}
