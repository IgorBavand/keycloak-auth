package com.keycloak.keycloakauth.controller;

import com.keycloak.keycloakauth.dto.UserLoginRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity<String> token(@RequestBody UserLoginRequest request) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", request.clientId());
        formData.add("username", request.username());
        formData.add("password", request.password());
        formData.add("grant_type", request.grantType());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        return restTemplate.postForEntity("http://localhost:8080/realms/dev/protocol/openid-connect/token", entity, String.class);
    }
}
