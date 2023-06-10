package com.apps.nmec.controllers;

import com.apps.nmec.enums.ERole;
import com.apps.nmec.models.UserModel;
import com.apps.nmec.services.ViewUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-view")
public class ViewUsersController {

    @Autowired
    private ViewUsersService viewUsersService;

    @GetMapping("/all/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModelList = viewUsersService.getAllUsers();
        return ResponseEntity.ok().body(userModelList);
    }
    @GetMapping("/added/users")
    @PreAuthorize("hasAnyAuthority('ADMIN','COUNSELOR','STAFF')")
    public ResponseEntity<List<UserModel>> fetchAddedUsers(@CurrentSecurityContext(expression="authentication?.name") String xAuthUser){
        List<UserModel> userModelList = viewUsersService.fetchAddedUsers(xAuthUser);
        return ResponseEntity.ok().body(userModelList);
    }

    @GetMapping("/createdby/{emailId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserModel>> fetchUsersCreatedByEmailId(@PathVariable String emailId){
        List<UserModel> userModelList = viewUsersService.fetchUsersCreatedByEmailId(emailId);
        return ResponseEntity.ok().body(userModelList);
    }

    @GetMapping("/all/counsellors")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public ResponseEntity<List<UserModel>> getAllCounsellors(){
        List<UserModel> userModelList = viewUsersService.getAllUsers().stream().filter(user->user.getRoles().contains(ERole.COUNSELOR)).collect(Collectors.toList());
        return ResponseEntity.ok().body(userModelList);
    }

}
