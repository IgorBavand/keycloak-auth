package com.keycloak.keycloakauth.controller;

import com.keycloak.keycloakauth.dto.UserLoginRecord;
import com.keycloak.keycloakauth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<String> generateUserToken(@RequestBody UserLoginRecord userLoginRecord) {
        return authenticationService.generateUserToken(userLoginRecord);
    }
}
