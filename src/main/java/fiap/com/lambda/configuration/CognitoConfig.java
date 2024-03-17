package fiap.com.lambda.configuration;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

public class CognitoConfig {

    @Singleton
    @Produces
    public AWSCognitoIdentityProvider cognitoClient() {
        return AWSCognitoIdentityProviderClient.builder()
                .withRegion("us-east-2")
                .build();
    }
}
