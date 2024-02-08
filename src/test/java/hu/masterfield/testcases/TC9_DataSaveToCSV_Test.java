package hu.masterfield.testcases;
import hu.masterfield.datatypes.Saving;
import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A létrehozott "Savings" típusú számlák adatainakj (account name, type, balance, ownership) lementése
 * egy --dumpSavings.csv-- fájlba, a --target-- könyvtárban kialakított helyre.
 */
public class TC9_DataSaveToCSV_Test extends BaseTest {
    protected static Logger logger = LogManager.getLogger(TC9_DataSaveToCSV_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC9_DataSaveToCSV_Test")
    @Description("TC9 - A létrehozott Savings típusú számlák adatainak lementése CSV fájlba.")
    @Tag("TC9")
    @Tag("Saving")
    public void TC9_DataSaveToCSV(TestInfo testInfo) {
        logger.info(testInfo.getDisplayName() + " started.");

        //Cookiek törlése
        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        gdprBannerPage.acceptCookies();

        //Login megvalósítása
        String username = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(username, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        homePage.goToViewSavingsPage();
        ViewSavingsAccountsPage viewSavingsAccountsPage = new ViewSavingsAccountsPage(driver);
        assertTrue(viewSavingsAccountsPage.isLoaded());

        // Számlaadatok lekérése és mentése
        List<Saving> savingList = viewSavingsAccountsPage.getAllSavings();
        saveSavingsToCSV(savingList);

    }

    private void saveSavingsToCSV(List<Saving> savingList) {
        String filePath = "target/dumpSavings.csv";
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            csvWriter.append("Account Name,Type,Balance,Ownership\n");
            for (Saving saving : savingList) {
                csvWriter.append(String.join(",", saving.getAccountName(), saving.getAccountTypes(),
                        saving.getOpeningBalance(), saving.getOwnershipTypes()));
                csvWriter.append("\n");
            }
            logger.info("Savings data saved to CSV file: " + filePath);
        } catch (IOException ex) {
            logger.error("Error occured while saving Savings data to CSV file: " + ex.getMessage());
        }
    }
}
