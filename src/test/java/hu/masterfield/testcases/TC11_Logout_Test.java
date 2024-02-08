package hu.masterfield.testcases;


import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC11 - Sikeres kijelentkezés vizsgálata
 */
public class TC11_Logout_Test extends BaseTest {
    protected static Logger logger = LogManager.getLogger(TC11_Logout_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC11_Logout_Test")
    @Description("TC11 - Sikeres kijelentkezés vizsgálata")
    @Tag("TC11")
    @Tag("Logout")
    public void test_Logout(TestInfo testInfo) throws InterruptedException, IOException {
        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        logger.info(testInfo + "started");

        /*Cooie-k elfogadása*/
        gdprBannerPage.isCookieMessageVisible();
        gdprBannerPage.acceptCookies();
        logger.info("gdprBannerPage.acceptCookies() called.");
        takesScreenshot();

        /*Bejelentkezés*/
        String username = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());


        /*Home page betöltése*/
        HomePage homePage = loginPage.login(username, password);
        assertTrue(homePage.isLoaded());
        takesScreenshot();

        /*Kijelentkezés elindítása*/
        homePage.logout();
        assertTrue(loginPage.isLoaded());
        logger.info("logout started");

        /**
         * Ellenőrzés, hogy a Login oldal minden eleme megjelent és a felbukkanó,
         * a sikeres kijelentkezést megerősítő elem ellenőrzése.
         */
        loginPage.validateLogout();
        takesScreenshot();


    }

}
