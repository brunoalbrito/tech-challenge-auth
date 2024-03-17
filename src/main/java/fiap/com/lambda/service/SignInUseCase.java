package fiap.com.lambda.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import jakarta.inject.Inject;

import java.util.Map;

public class SignInUseCase {


    public String signIn(String cpf, String password) {
        try {

            AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClient.builder()
                    .withRegion("us-west-2")
                    .build();

            InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest()
                    .withClientId("1rq3hk963u392u664a5is9j1vv")
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
