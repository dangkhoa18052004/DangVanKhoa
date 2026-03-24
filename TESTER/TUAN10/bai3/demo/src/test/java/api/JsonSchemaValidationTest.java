package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidationTest extends ApiBaseTest {

    @Test
    public void testUserListSchema() {
        given(requestSpec)
                .when()
                .get("/users?page=1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/user-list-schema.json"));
    }

    @Test
    public void testSingleUserSchema() {
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

    @Test
    public void testCreateUserSchema() {
        CreateUserRequest requestBody = new CreateUserRequest("Dang Van Khoa", "Tester");

        given(requestSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }
}