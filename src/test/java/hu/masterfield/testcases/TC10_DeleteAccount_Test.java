package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.ViewSavingsAccountsPage;
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
 * TC10 - Accountok sikeres törlésének vizsgálata
 */

public class TC10_DeleteAccount_Test extends BaseTest {

    protected static Logger logger = LogManager.getLogger(TC10_DeleteAccount_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC10_DeleteAccount_Test")
    @Description("TC10 - Accountok sikeres törlésének vizsgálata")
    @Tag("TC10")
    @Tag("Delete")
    public void test_TC10_DeleteAccount(TestInfo testInfo) throws IOException {
        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        logger.info(testInfo);

        gdprBannerPage.acceptCookies();

        /*Bejelentkezés */
        String userName = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
        logger.info("Login is started.");

        /*Home page megnyitása*/
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isLoaded());
        logger.info("homePage.isLoaded()");

        /*accountok törlése*/
        homePage.deleteData();
        logger.info("delete data form account");

        homePage.goToViewSavingsPage();

        /*View savings page megnyitása*/
        ViewSavingsAccountsPage viewSavingsAccountsPage = new ViewSavingsAccountsPage(driver);
        logger.info("viewSavingsAccountsPage.isLoaded()");

        /*No Account szöveg megjelenése*/
        viewSavingsAccountsPage.noAccountsIsLoaded();
        takesScreenshot();
        logger.info("noAccountsIsLoaded() started");
    }

}
