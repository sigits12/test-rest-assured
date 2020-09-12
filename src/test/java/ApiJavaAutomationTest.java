import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.response.Response;
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
    public void getListUsers() {
        Response response = userController.getAllStudents();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("The response status is " + statusCode);
    }

}
