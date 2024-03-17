package fiap.com.lambda;

import br.com.caelum.stella.validation.CPFValidator;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.com.lambda.domain.AuthUser;
import fiap.com.lambda.service.CreateUserUseCase;
import fiap.com.lambda.service.SignInUseCase;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("handler")
public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        if (event.getPath().contains("/health")) {
            return new APIGatewayProxyResponseEvent()
                    .withBody("OK")
                    .withStatusCode(200);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        if (event.getPath().contains("/auth/sign-up")) {

            try {
                AuthUser authUser = objectMapper.readValue(event.getBody(), AuthUser.class);

                CPFValidator cpfValidator = new CPFValidator();
                if (!cpfValidator.isEligible(authUser.cpf())) {
                    return new APIGatewayProxyResponseEvent()
                            .withBody("CPF inválido")
                            .withStatusCode(400);
                }

                CreateUserUseCase createUserUseCase = new CreateUserUseCase();
                var response = createUserUseCase.createUser(authUser);

                return new APIGatewayProxyResponseEvent()
                        .withBody(objectMapper.writeValueAsString(response))
                        .withStatusCode(201);
            } catch (JsonProcessingException e) {
                return new APIGatewayProxyResponseEvent()
                        .withBody("Erro ao processar a requisição")
                        .withStatusCode(500);
            }
        }

        if(event.getPath().contains("/auth/sign-in")) {
            try {
                AuthUser authUser = objectMapper.readValue(event.getBody(), AuthUser.class);

                SignInUseCase signInUseCase = new SignInUseCase();
                var response = signInUseCase.signIn(authUser.cpf(), authUser.password());

                return new APIGatewayProxyResponseEvent()
                        .withBody(objectMapper.writeValueAsString(response))
                        .withStatusCode(200);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return new APIGatewayProxyResponseEvent()
                .withBody("Rota não encontrada")
                .withStatusCode(404);
    }
}
