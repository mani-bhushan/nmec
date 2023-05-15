package com.apps.nmec.controllers;

import com.apps.nmec.models.UserModel;
import com.apps.nmec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class UsersControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> userModelList = userService.getAllUsers();
        return ResponseEntity.ok().body(userModelList);
    }

    @PostMapping("/user/add")
    public ResponseEntity<List<UserModel>> addUsers(@RequestBody List<UserModel> userModelList){

        List<UserModel> response = userService.createUsers(userModelList);
        return new ResponseEntity<List<UserModel>>(response, HttpStatus.CREATED);
    }

}
