package controller;


import defaultrequest.DefaultRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

/**
 * Date 12/09/20
 *
 * @author sigit
 */
public class UserController extends DefaultRequest {

    private final static String baseUri = "http://localhost:8080";

    public Response getAllUsers() {
        Response response = service()
                .get("/users");

        return response;
    }

    public Response getSingleUser(Long id) {
        Response response = service()
                .pathParam("id", id)
                .get("/users/{id}");

        return response;
    }

    public Response addUser(User userRequest) {

        Response response = service()
                .body(userRequest)
                .post("/users");

        return response;

    }

}
