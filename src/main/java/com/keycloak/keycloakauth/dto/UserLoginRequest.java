package com.keycloak.keycloakauth.dto;

public record UserLoginRequest(String password, String clientId, String grantType, String username) { }
