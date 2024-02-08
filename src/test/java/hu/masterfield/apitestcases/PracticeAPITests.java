package hu.masterfield.apitestcases;

import hu.masterfield.utils.Consts;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PracticeAPITests extends BaseAPITest {
    /**
     * User lekérdezése
     */
    @Test
    public void getUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/user");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("id", is(4))
                .body("username", equalTo("admin@demo.io"))
                .body("userProfile.id", is(5));
    }

    @Test
    public void getUserProfile() {
        String userID = globalTestData.getProperty(Consts.USER_ID);
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("userId", userID)
                .when()
                .get("/api/v1/user/{userId}/profile");
        response.prettyPrint();
        response.then()
                .body("userProfile.id", is(76))
                .body("firstName", equalTo("Josh"))
                .body("lastName", equalTo("Smith"))
                .body("title", equalTo("Mr"))
                .body("gender", equalTo("M"))
                .body("ssn", equalTo("573-51-2195"));
    }

    @Test
    public void getUserByUsername() {
        String userName = globalTestData.getProperty(Consts.USER_NAME);
        Response response = given().log().params()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", userName)
                .when()
                .get("/api/v1/user/find");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(userName));
    }

    @Test
    public void getAllAccounts() {
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer "+ authToken)
                .when()
                .get("/api/v1/account");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("accountType.id[[0]", is(11))
                .body("accountType.id[[1]", is(8))
                .body("accountType.id[[2]", is(10))
                .body("accountType.id[[3]", is(11));
    }
}
