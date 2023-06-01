package com.apps.nmec.services;

import com.apps.nmec.mappers.UserMapper;
import com.apps.nmec.models.UserModel;
import com.apps.nmec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewUsersService {

    @Autowired private UserMapper userMapper;
    @Autowired private UserRepository userRepository;


    public List<UserModel> getAllUsers() {
        return userMapper.mapUserEntityListToModelList(userRepository.findAll());
    }

    public List<UserModel> fetchAddedUsers(final String xAuthUser) {
        return userMapper.mapUserEntityListToModelList(userRepository.findByInitiatedBy(xAuthUser));
    }

    public List<UserModel> fetchUsersCreatedByEmailId(final String emailId) {
        return userMapper.mapUserEntityListToModelList(userRepository.findByInitiatedBy(emailId));
    }

}
