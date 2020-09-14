package controller;


import defaultrequest.DefaultRequest;
import io.restassured.response.Response;
import model.User;

/**
 * Date 12/09/20
 *
 * @author sigit
 */
public class UserController extends DefaultRequest {

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

    public Response updateSingleUser(User userRequest, Long id) {
        Response response = service()
                .body(userRequest)
                .pathParam("id", id)
                .put("/users/{id}");

        return response;
    }

}
