package com.apps.nmec.controllers;

import com.apps.nmec.entities.RoleEntity;
import com.apps.nmec.entities.UserEntity;
import com.apps.nmec.requests.AuthRequest;
import com.apps.nmec.responses.AuthResponse;
import com.apps.nmec.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;

	@Operation(summary = "login user")
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			UserEntity userEntity = (UserEntity) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(userEntity);
			AuthResponse response = new AuthResponse(userEntity.getEmail()
					, userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet())
					, accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
