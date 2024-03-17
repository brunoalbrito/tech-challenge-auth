package fiap.com.lambda.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import fiap.com.lambda.configuration.CognitoConfig;
import fiap.com.lambda.domain.AuthUser;

import java.util.HashMap;
import java.util.Map;
public class CreateUserUseCase {
    private static final String CLIENT_ID = "1rq3hk963u392u664a5is9j1vv";

    public Map<String, String> createUser(AuthUser authUser) {

        AWSCognitoIdentityProvider cognitoClient = CognitoConfig.cognitoClient();

        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(CLIENT_ID)
                .withUsername(authUser.cpf())
                .withPassword(authUser.password());

        SignUpResult signUpResult = cognitoClient.signUp(signUpRequest);

        Map<String, String> userAttributes = new HashMap<>();
        userAttributes.put("message", "User created successfully");
        userAttributes.put("userId", signUpResult.getUserSub());

        return userAttributes;
    }
}
