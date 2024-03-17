package fiap.com.lambda.configuration;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;

public class CognitoConfig {

    public static AWSCognitoIdentityProvider cognitoClient() {
        return AWSCognitoIdentityProviderClient.builder()
                .withRegion("us-east-1")
                .build();
    }
}
