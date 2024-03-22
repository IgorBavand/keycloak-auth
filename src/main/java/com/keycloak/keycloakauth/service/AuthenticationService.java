package com.keycloak.keycloakauth.service;

import com.keycloak.keycloakauth.dto.UserLoginRecord;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    @Value("${keycloak.domain}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-ids.token}")
    private String clientId;

    public ResponseEntity<String> generateUserToken(UserLoginRecord userLoginRecord) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", clientId);
        formData.add("username", userLoginRecord.username());
        formData.add("password", userLoginRecord.password());
        formData.add("grant_type", OAuth2Constants.PASSWORD);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        return restTemplate.postForEntity(serverUrl + "/realms/" + realm + "/protocol/openid-connect/token", entity, String.class);
    }
}
