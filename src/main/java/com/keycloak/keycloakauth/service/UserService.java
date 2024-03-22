package com.keycloak.keycloakauth.service;

import com.keycloak.keycloakauth.dto.UserRegistrationRecord;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    public UserRepresentation createNewUser(UserRegistrationRecord userRegistrationRecord) {
        var userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(userRegistrationRecord.username());
        userRepresentation.setFirstName(userRegistrationRecord.firstName());
        userRepresentation.setLastName(userRegistrationRecord.lastName());
        userRepresentation.setEmail(userRegistrationRecord.email());
        userRepresentation.setEmailVerified(true);

        var credentials = new CredentialRepresentation();
        credentials.setValue(userRegistrationRecord.password());
        credentials.setTemporary(false);
        credentials.setType(OAuth2Constants.PASSWORD);

        userRepresentation.setCredentials(List.of(credentials));

        RealmResource realm1 = keycloak.realm(realm);
        UsersResource usersResource = realm1.users();

        var response = usersResource.create(userRepresentation);

        if (Objects.equals(response.getStatus(), HttpStatus.CREATED.value())) {
            return userRepresentation;
        }

        return null;
    }
}
