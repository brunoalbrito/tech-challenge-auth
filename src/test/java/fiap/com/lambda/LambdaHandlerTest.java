package fiap.com.lambda;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class LambdaHandlerTest {
    @Test
    void testSimpleLambdaSuccess() throws Exception {
        // you test your lambdas by invoking on http://localhost:8081
        // this works in dev mode too
        given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\"path\":\"/health\"}")
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(containsString("OK"));
    }

}
