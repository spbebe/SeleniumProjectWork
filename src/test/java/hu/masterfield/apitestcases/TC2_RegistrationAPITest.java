package hu.masterfield.apitestcases;

import hu.masterfield.datatypes.RegistrationData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.swing.text.AbstractDocument;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC2_RegistrationAPITest extends BaseAPITest {
    /**
     * Ez a teszt regisztrál egy profilt a globalTestData properties fileban
     * megadott adatokkal, ellenőrzi a profil létrejöttét.
     *
     * POST /api/v1/user
     *
     * createNewUser
     * user-controller -> /api/v1/user
     */

    protected static Logger logger = LogManager.getLogger(TC2_RegistrationAPITest.class);

    @Test
    public void testCreateNewUser() {
        RegistrationData registrationData = new RegistrationData();

        /* A bodyban megadjuk a regisztrációhoz szükséges adatokat */
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("address", registrationData.getAddress());
        requestBody.put("country", registrationData.getCountry());
        requestBody.put("dob", registrationData.getDateOfBirth());
        requestBody.put("emailAddress", registrationData.getEmailAddress());
        requestBody.put("firstName", registrationData.getFirstName());
        requestBody.put("gender", registrationData.getGender());
        requestBody.put("homePhone", registrationData.getHomePhone());
        requestBody.put("lastName", registrationData.getLastName());
        requestBody.put("locality", registrationData.getLocality());
        requestBody.put("mobilePhone", registrationData.getMobilePhone());
        requestBody.put("password", registrationData.getPassword());
        requestBody.put("postalCode", registrationData.getPostalCode());
        requestBody.put("region", registrationData.getRegion());
        requestBody.put("ssn", registrationData.getSocialSecurityNumber());
        requestBody.put("title", registrationData.getTitle());
        requestBody.put("workPhone", registrationData.getWorkPhone());


        logger.info("Start /api/v1/user POST method.");
        /* POST kérés küldése */
        given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("role", "USER")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(201);

        logger.info("End /api/v1/user POST method");


        /* A globalTestData.properties fileból kiolvasott emailcímet ellenőrizzük, amivel regisztráltunk
         */

        String emailAddress = registrationData.getEmailAddress();

        /* A regisztrációs adatoknál megadott email címet felhasználva megkeressük a profilt és
         kiírjuk a profil adatait.
        */

        logger.info("Start /api/v1/user/find GET method");
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", emailAddress)
                .when()
                .get("/api/v1/user/find");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(emailAddress));

        logger.info("End /api/v1/user/find GET method.");

        /* Ellenőrizzük, hogy a válaszban helyesek-e a regisztrációs adatok */
        assertEquals(registrationData.getFirstName(), response.path("userProfile.firstName"));
        assertEquals(registrationData.getLastName(), response.path("userProfile.lastName"));
        assertEquals(registrationData.getEmailAddress(), response.path("userProfile.emailAddress"));
        assertEquals(registrationData.getSocialSecurityNumber(), response.path("userProfile.ssn"));
        assertEquals(registrationData.getAddress(), response.path("userProfile.address"));
        assertEquals(registrationData.getCountry(), response.path("userProfile.country"));
        assertEquals(registrationData.getDateOfBirth(), response.path("userProfile.dob"));
        assertEquals(registrationData.getGender(), response.path("userProfile.gender"));
        assertEquals(registrationData.getTitle(), response.path("userProfile.title"));
        assertEquals(registrationData.getLocality(), response.path("userProfile.locality"));
        assertEquals(registrationData.getPostalCode(), response.path("userProfile.postalCode"));
        assertEquals(registrationData.getHomePhone(), response.path("userProfile.homePhone"));
        assertEquals(registrationData.getWorkPhone(), response.path("userProfile.workPhone"));
    }
}
