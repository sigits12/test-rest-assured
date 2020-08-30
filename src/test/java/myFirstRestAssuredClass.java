import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

/**
 * Date 30/08/20
 *
 * @author sigit
 */
public class myFirstRestAssuredClass {

    final static String secondUrl = "https://reqres.in";

    @Test
    public void firstTrial(){
        Response response =
                given()
                        .baseUri(secondUrl)
                        .basePath("/api/users")
                        .queryParam("page",2)
                        .contentType(ContentType.JSON)
                        .get();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("The response status is " +statusCode);
        System.out.println("First user's first_name = " + response.path("data.first_name[0]"));
        System.out.println("Second user's first_name = "+ response.path("data.first_name[1]"));
        Assert.assertEquals("Michael", response.path("data.first_name[0]"));
    }

    @Test
    public void getResponseBody(){

        Response response =
                given().log().all()
                        .baseUri(secondUrl)
                        .basePath("/api/").contentType(ContentType.JSON)
                        .param("page", 2)
                        .param("per_page", 4)
                        .get("users");

        response.getBody().prettyPrint();
    }

    @Test
    public void tryPathParameters(){
        Response response =
                given().log().all()
                        .baseUri(secondUrl)
                        .basePath("/api/")
                        .contentType(ContentType.JSON)
                        .pathParam("id", 2)
                        .get("users/{id}");

        response.getBody().prettyPrint();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals("janet.weaver@reqres.in", response.path("data.email"));
        System.out.println("The response status is " +statusCode);
        System.out.println("User's email with id 2 = " + response.path("data.email"));
    }

    @Test
    public void postCreateUser(){
        String bodyRequest = "{\"name\":\"Andi\",\"job\":\"developer\"}";

        Response response =
                given()
                        .baseUri(secondUrl)
                        .basePath("/api")
                        .contentType(ContentType.JSON)
                        .body(bodyRequest)
                        .post("/users");

        response.getBody().prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.path("name"), "Andi");
        Assert.assertEquals(response.path("job"), "developer");
        String idEmployee = response.path("id");
        System.out.println("ID employee = " + idEmployee);
    }
}