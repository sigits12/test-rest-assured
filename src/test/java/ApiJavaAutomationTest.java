import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date 12/09/20
 *
 * @author sigit
 */
public class ApiJavaAutomationTest {

    public Faker faker = new Faker();

    public String generateRandomName() {
        return faker.name().fullName();
    }

    public String generateRandomCountry() {
        return faker.country().name();
    }

    UserController userController = new UserController();

    @Test
    public void getAllUsers() {
        Response response = userController.getAllUsers();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("The response status is " + statusCode);
    }

    @Test
    public void getSingleUser() {
        Response responseGetAll = userController.getAllUsers();

        Long id = new Long(responseGetAll.path("id[0]").toString());
        Response responseGetSingle = userController.getSingleUser(id);

        int statusCode = responseGetSingle.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("The response status is " + statusCode);

        Assert.assertThat(responseGetSingle.getBody().asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-single-user.json"));

    }

    @Test
    public void addUser() {
        // Serialization
        User userRequest = new User();
        userRequest.setName(generateRandomName());
        userRequest.setCountry(generateRandomCountry());

        Response response = userController.addUser(userRequest);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("The response status is " + statusCode);

        // Deserialization
        User userResponse = response.getBody(). as(User.class);
        System.out.println("id\t : " + userResponse.getId());
        System.out.println("name\t : " + userResponse.getName());
        System.out.println("country\t : " + userResponse.getCountry());

    }

}
