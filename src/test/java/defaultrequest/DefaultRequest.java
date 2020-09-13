package defaultrequest;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Date 13/09/20
 *
 * @author sigit
 */
public class DefaultRequest {

    public RequestSpecification service() {
        return given().
                log().all()
                .baseUri("http://localhost:8080")
                .basePath("/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}
