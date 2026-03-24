package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserGetApiTest extends ApiBaseTest {

    @Test
    public void testGetUsers() {
        given(requestSpec)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testGetUserById3() {
        given(requestSpec)
                .when()
                .get("/users/3")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", equalTo(3))
                .body("email", not(isEmptyOrNullString()))
                .body("name", not(isEmptyOrNullString()))
                .body("username", not(isEmptyOrNullString()));
    }

    @Test
    public void testGetUserById1() {
        given(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", equalTo(1))
                .body("email", not(isEmptyOrNullString()))
                .body("name", not(isEmptyOrNullString()));
    }

    @Test
    public void testGetUserNotFound() {
        given(requestSpec)
                .when()
                .get("/users/9999")
                .then()
                .statusCode(404);
    }
}