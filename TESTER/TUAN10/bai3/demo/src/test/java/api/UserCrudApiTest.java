package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserCrudApiTest extends ApiBaseTest {

    private static String createdUserId;
    private static String createdAtValue;

    @Test(priority = 1)
    public void testCreateUser() {
        CreateUserRequest requestBody = new CreateUserRequest("Dang Van Khoa", "Tester");

        Response response = given(requestSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .body("name", equalTo("Dang Van Khoa"))
                .body("job", equalTo("Tester"))
                .body("id", notNullValue())
                .extract()
                .response();

        createdUserId = response.jsonPath().getString("id");

        Assert.assertNotNull(createdUserId);
        Assert.assertFalse(createdUserId.isEmpty());
    }

    @Test(priority = 2)
    public void testUpdateUserPut() {
        CreateUserRequest requestBody = new CreateUserRequest("Dang Van Khoa", "Senior Tester");

        Response response = given(requestSpec)
                .body(requestBody)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("name", equalTo("Dang Van Khoa"))
                .body("job", equalTo("Senior Tester"))
                .extract()
                .response();
    }

    @Test(priority = 3)
    public void testUpdateUserPatch() {
        Map<String, String> patchBody = new HashMap<>();
        patchBody.put("job", "Lead Tester");

        given(requestSpec)
                .body(patchBody)
                .when()
                .patch("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("job", equalTo("Lead Tester"));
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(200) // JSONPlaceholder returns 200 on DELETE
                .body(equalTo("{}")); // JSONPlaceholder returns empty JSON {} on DELETE
    }

    @Test(priority = 5)
    public void testPostThenGetConfirm() {
        CreateUserRequest requestBody = new CreateUserRequest("Dang Van Khoa", "QA");

        Response postResponse = given(requestSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .body("name", equalTo("Dang Van Khoa"))
                .body("job", equalTo("QA"))
                .body("id", notNullValue())
                .extract()
                .response();

        String newId = postResponse.jsonPath().getString("id");

        given(requestSpec)
                .when()
                .get("/users/" + newId)
                .then()
                .statusCode(anyOf(is(200), is(404)));
    }
}