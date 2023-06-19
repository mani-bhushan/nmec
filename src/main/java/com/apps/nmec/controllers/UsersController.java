package com.apps.nmec.controllers;

import com.apps.nmec.models.UserModel;
import com.apps.nmec.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "add user")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELLOR','STAFF')")
    public ResponseEntity<UserModel> addUser(@RequestBody @Valid UserModel userModel) {
        final UserModel response = userService.createUser(userModel);
        return new ResponseEntity<UserModel>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "update role")
    @PatchMapping("/update/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserModel> updateRoles(@RequestBody @Valid UserModel userModel) {

        UserModel response = userService.updateUserRole(userModel);
        return new ResponseEntity<UserModel>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "update user password")
    @PatchMapping("/update/password")
    public ResponseEntity<Boolean>updateUserPassword(@RequestBody UserModel userModel) {
        return new ResponseEntity<Boolean>(userService.updateUserPassword(userModel), HttpStatus.ACCEPTED);
    }

}
