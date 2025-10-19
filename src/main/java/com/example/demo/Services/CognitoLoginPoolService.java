package com.example.demo.Services;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.Map;

@Service
public class CognitoLoginPoolService {
    private final CognitoIdentityProviderClient cognitoClient;
    private final String userPoolId = "ap-southeast-2_XtzNgfdRa";      // ← REPLACE WITH YOUR POOL ID
    private final String clientId = "15pmejasmv5ri20urllr9bi1kl";       // ← REPLACE WITH YOUR CLIENT ID

    public CognitoLoginPoolService() {
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .region((Region.AP_SOUTHEAST_2)) // ← CHANGE IF NEEDED
                .build();
    }

    public String authenticateUser(String email, String password) {
        AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                .userPoolId(userPoolId)
                .clientId(clientId)
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .authParameters(Map.of(
                        "USERNAME", email,
                        "PASSWORD", password))
                .build();

        AdminInitiateAuthResponse response = cognitoClient.adminInitiateAuth(authRequest);
        return response.authenticationResult().idToken();
    }

    public void createUser(String email, String password, String name) {
        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .userAttributes(
                        AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("name").value(name).build()
                )
                .temporaryPassword(password)
                .messageAction(MessageActionType.SUPPRESS)
                .desiredDeliveryMediums(DeliveryMediumType.EMAIL) // optional
                .build();

        cognitoClient.adminCreateUser(createUserRequest);

        // Set permanent password AND confirm user
        AdminSetUserPasswordRequest setPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .password(password)
                .permanent(true)
                .build();

        cognitoClient.adminSetUserPassword(setPasswordRequest);

        // 👇 Add this line to confirm the user
        AdminUpdateUserAttributesRequest updateUserRequest = AdminUpdateUserAttributesRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .userAttributes(AttributeType.builder().name("email_verified").value("true").build())
                .build();

        cognitoClient.adminUpdateUserAttributes(updateUserRequest);
    }
}
