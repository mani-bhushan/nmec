package com.apps.nmec.controllers;

import com.apps.nmec.models.UserModel;
import com.apps.nmec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired private UserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELOR','STAFF')")
    public ResponseEntity<UserModel> addUser(@RequestBody @Valid UserModel userModel) {
        UserModel response = userService.createUser(userModel);
        return new ResponseEntity<UserModel>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/update/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserModel> updateRoles(@RequestBody @Valid UserModel userModel) {

        UserModel response = userService.updateUserRole(userModel);
        return new ResponseEntity<UserModel>(response, HttpStatus.CREATED);
    }

}
