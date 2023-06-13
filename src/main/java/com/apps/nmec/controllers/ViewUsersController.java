package com.apps.nmec.controllers;

import com.apps.nmec.enums.ERole;
import com.apps.nmec.models.UserModel;
import com.apps.nmec.services.ViewUsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-view")
public class ViewUsersController {

    @Autowired
    private ViewUsersService viewUsersService;

    @Operation(summary = "all users")
    @GetMapping("/all/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModelList = viewUsersService.getAllUsers();
        return ResponseEntity.ok().body(userModelList);
    }

    @Operation(summary = "all users")
    @GetMapping("/added/users")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELOR','STAFF')")
    public ResponseEntity<List<UserModel>> fetchAddedUsers(@CurrentSecurityContext(expression="authentication?.name") String xAuthUser){
        List<UserModel> userModelList = viewUsersService.fetchAddedUsers(xAuthUser);
        return ResponseEntity.ok().body(userModelList);
    }

    @Operation(summary = "all users added by user")
    @GetMapping("/createdby/{emailId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserModel>> fetchUsersCreatedByEmailId(@PathVariable String emailId){
        List<UserModel> userModelList = viewUsersService.fetchUsersCreatedByEmailId(emailId);
        return ResponseEntity.ok().body(userModelList);
    }

    @Operation(summary = "all counsellors")
    @GetMapping("/all/counsellors")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public ResponseEntity<List<UserModel>> getAllCounsellors(){
        List<UserModel> userModelList = viewUsersService.getAllUsers().stream().filter(user->user.getRoles().contains(ERole.COUNSELLOR)).collect(Collectors.toList());
        return ResponseEntity.ok().body(userModelList);
    }

}
