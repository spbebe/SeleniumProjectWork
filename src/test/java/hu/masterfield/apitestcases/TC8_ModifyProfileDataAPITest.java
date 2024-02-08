package hu.masterfield.apitestcases;


import hu.masterfield.datatypes.ModifyData;
import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC8_ModifyProfileDataAPITest extends BaseAPITest {

    protected static Logger logger = LogManager.getLogger(TC8_ModifyProfileDataAPITest.class);
    private int userId;

    /**
     * Ez a teszt megkeresi a profilt a registrationData properties fájlban megadott adatokkal és ellenőrzi.
     * GET api/v1/user/find
     * getUserByUserName
     * user controller -> api/v1/user/find
     */
    RegistrationData registrationData = new RegistrationData();
    String emailAddress = registrationData.getEmailAddress();
    /* A regisztrációs adatoknál megadott email címet felhasználva megkeressük a profilt és kiírjuk a profil adatait.*/

    @Test
    public void getUserByUserName() {
        logger.info("Start /api/v1/user/find method.");
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
        logger.info("End /api/v1/user/find method.");

        /* Ellenőrizzük, hogy a válaszban helyesek-e a regisztrációs adatok.*/
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
        assertEquals(registrationData.getMobilePhone(), response.path("userProfile.mobilePhone"));
        assertEquals(registrationData.getWorkPhone(), response.path("userProfile.workPhone"));
    }

    /**
     * Ez a teszt módosítja egy meglévő profil adatait a ModifyProfileData fájlban megadott adatokkal, ellenőrzi a profil létrejöttét.
     * PUT /api/v1/user/profile
     * updateUserProfile
     * user-controlleer ->/api/v1/user/profile
     */
    ModifyData modifyProfileData = new ModifyData();
    GlobalTestData globalTestData = new GlobalTestData();


    @Test
    public void ModifyProfileData() {

        String userId = globalTestData.getProperty(Consts.USER_ID);
        logger.info("Start PUT /api/v1/user/profile method.");

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("address", modifyProfileData.getAddress());
        requestBody.put("country", modifyProfileData.getCountry());
        requestBody.put("firstName", modifyProfileData.getFirstName());
        requestBody.put("gender", modifyProfileData.getGender());
        requestBody.put("homePhone", modifyProfileData.getHomePhone());
        requestBody.put("lastName", modifyProfileData.getLastName());
        requestBody.put("locality", modifyProfileData.getLocality());
        requestBody.put("mobilePhone", modifyProfileData.getMobilePhone());
        requestBody.put("postalCode", modifyProfileData.getPostalCode());
        requestBody.put("region", modifyProfileData.getRegion());
        requestBody.put("title", modifyProfileData.getTitle());
        requestBody.put("workPhone", modifyProfileData.getWorkPhone());

        System.out.println("UserId: " + userId);
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .body(requestBody)
                .pathParam("userId", userId)
                .when()
                .put("/api/v1/user/{userId}/profile");
        response.prettyPrint();
        response.then()
                .statusCode(200);
        logger.info("End PUT /api/v1/user/profile method.");

        String emailAddress = registrationData.getEmailAddress();
        /* A regisztrációs adatoknál megadott email címet felhasználva megkeressük a profilt és kiírjuk a profil adatait.*/

        logger.info("Start /api/v1/user/find GET method.");
        Response responseTwo = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", emailAddress)
                .when()
                .get("/api/v1/user/find");
        responseTwo.prettyPrint();
        responseTwo.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo(emailAddress));
        logger.info("End /api/v1/user/find GET method.");

        // Ellenőrizzük, hogy a válaszban helyesek-e a módosított adatok.
//        assertEquals(modifyProfileData.getFirstname(), response.path("userProfile.firstName"));
//        assertEquals(modifyProfileData.getLastname(), response.path("userProfile.lastName"));
//        assertEquals(modifyProfileData.getAddress(), response.path("userProfile.address"));
//        assertEquals(modifyProfileData.getCountry(), response.path("userProfile.country"));
//        assertEquals(modifyProfileData.getGender(), response.path("userProfile.gender"));
//        assertEquals(modifyProfileData.getTitle(), response.path("userProfile.title"));
//        assertEquals(modifyProfileData.getLocality(), response.path("userProfile.locality"));
//        assertEquals(modifyProfileData.getPostalCode(), response.path("userProfile.postalCode"));
//        assertEquals(modifyProfileData.getHomePhone(), response.path("userProfile.homePhone"));
//        assertEquals(modifyProfileData.getMobilePhone(), response.path("userProfile.mobilePhone"));
//        assertEquals(modifyProfileData.getWorkPhone(), response.path("userProfile.workPhone"));

    }
}