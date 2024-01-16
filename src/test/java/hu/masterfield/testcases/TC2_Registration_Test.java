package hu.masterfield.testcases;

import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.RegistrationFirstPage;
import hu.masterfield.pages.RegistrationSecondPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * TC2 - Sikeres regisztráció érvényes adatok megadásával.
 */
public class TC2_Registration_Test extends BaseTest {
    private static Logger logger = LogManager.getLogger(TC2_Registration_Test.class);


    @Test
    @DisplayName("TC2_Registration")
    @Description("TC2 - Sikeres regisztráció tesztelése érvényes adatokkal")
    @Tag("TC2")
    @Tag("Regisztráció")
    public void TC2_Registration_Test(TestInfo testInfo) throws IOException, InterruptedException {
        Thread.sleep(10000);
        logger.info(testInfo + " started.");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* a süti elfogadására szolgáló ablak megjelenésének ellenőrzése */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");

        logger.info("Registration");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());
        loginPage.registrationStart();

        RegistrationData registrationData = new RegistrationData();
        logger.info(registrationData);

        logger.info("RegistrationFirstPage betöltése");
        RegistrationFirstPage registrationFirstPage = new RegistrationFirstPage(driver);
        assertTrue(registrationFirstPage.isLoaded());
        RegistrationSecondPage registrationSecondPage = registrationFirstPage.registrationFirstPage();



    }

}
