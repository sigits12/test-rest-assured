import com.github.javafaker.Faker;

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

}
