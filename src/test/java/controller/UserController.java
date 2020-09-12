package controller;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * Date 12/09/20
 *
 * @author sigit
 */
public class UserController {

    private final static String baseUri = "http://localhost:8080";

    public Response getAllUsers() {
        Response response = given()
                .log()
                .all()
                .baseUri(baseUri)
                .basePath("/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/users");

        return response;
    }

    public Response getSingleUser(Long id) {
        Response response = given()
                .log()
                .all()
                .baseUri(baseUri)
                .basePath("/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .get("/users/{id}");

        return response;
    }

}
