package fiap.com.lambda.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import fiap.com.lambda.configuration.CognitoConfig;

import java.util.Map;

public class SignInUseCase {

    public String signIn(String cpf, String password) {
        try {

            AWSCognitoIdentityProvider cognitoClient = CognitoConfig.cognitoClient();

            InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest()
                    .withClientId("5s45djf8ocqd7tnlm110bjvfqj")
                    .withAuthFlow("USER_PASSWORD_AUTH")
                    .withAuthParameters(Map.of(
                            "USERNAME", cpf,
                            "PASSWORD", password
                    ));

            return cognitoClient.initiateAuth(initiateAuthRequest).getAuthenticationResult().getIdToken();
        } catch (UserNotFoundException | NotAuthorizedException e) {
            // Handle authentication failures
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }
}
