package com.apps.nmec.controllers;

import com.apps.nmec.models.UserModel;
import com.apps.nmec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> userModelList = userService.getAllUsers();
        return ResponseEntity.ok().body(userModelList);
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> addUser(@RequestBody @Valid UserModel userModel){

        UserModel response = userService.createUser(userModel);
        return new ResponseEntity<UserModel>(response, HttpStatus.CREATED);
    }

}
