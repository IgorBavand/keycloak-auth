package com.keycloak.keycloakauth.controller;

import com.keycloak.keycloakauth.dto.UserRegistrationRecord;
import com.keycloak.keycloakauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("create-new-user")
    public UserRepresentation createNewUser (@RequestBody UserRegistrationRecord userRegistrationRecord) {
        return userService.createNewUser(userRegistrationRecord);
    }
}
