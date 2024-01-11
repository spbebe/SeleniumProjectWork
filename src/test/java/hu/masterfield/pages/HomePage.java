package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Az oldalon található elemek azonosítása. Ezek szükségesek a HomePageről induló műveletekhez.")
public class HomePage extends BasePage{
    /* Az oldalon található elemek azonosítása. Ezek szükségesek a HomePageről induló
            műveletekhez.
    */

    protected static Logger logger = LogManager.getLogger(HomePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();


    // Checking menu
    @FindBy(id="checking-menu")
    private WebElement checkingMenu;

    // Checking menu -> View Checking
    @FindBy(id="view-checking-menu-item")
    private WebElement viewCheckingMenuItem;

    // Checking menu -> New Checking
    @FindBy(id="new-checking-menu-item")
    private WebElement newCheckingMenuItem;

    // Savings menu
    @FindBy(id="savings-menu")
    private WebElement savingsMenu;

    // Savings menu -> View Savings
    @FindBy(id="view-savings-menu-item")
    private WebElement viewSavingsMenuItem;

    // Savings menu -> New Savings
    @FindBy(id="new-savings-menu-item")
    private WebElement newSavingsMenuItem;

    // Welcome message
    // @FindBy(css="div.page-title li")
    // @FindBy(css="div[class='page-title'] li")
    @FindBy(xpath="//div[@class='page-title']//li")
    private WebElement labelTitle;

    // User avatar / picture
    @FindBy(css="img.user-avatar.rounded-circle")
    private WebElement avatarDropdownMenuButton;

    // Avatal lenyitása -> My Porfile
    // @FindBy(css="a.nav-link[href='/bank/user/profile']")
    @FindBy(xpath="//a[contains(text(), 'My Profile')]")
    private WebElement avatarDropdownMyProfileLink;

    // Avatar lenyitása -> Change Password
    // @FindBy(css="a.nav-link[href='/bank/user/password']")
    @FindBy(xpath = "//a[contains(text(), 'Change Password')]")
    private WebElement avatarDropdownChangePasswordLink;

    // Avatar lenyitása -> Create Data
    // @FindBy(css="a.nav-link[href='/bank/user/create-data']")
    @FindBy(xpath = "//a[contains(text(), 'Create Data')]")
    private WebElement avatarDropdownCreateDataLink;

    // Avatar lenyitása -> Delete Data
    // @FindBy(css="a.nav-link[href=\"/bank/user/delete-data\"]")
    @FindBy(xpath = "//a[contains(text(), 'Delete Data')]")
    private WebElement avatarDropdownDeleteDataLink;

    // Avatar lenyitása -> Logout
    // @FindBy(css="a.nav-link[href=\"/bank/user/logout\"]")
    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement avatarDropdownLogoutLink;

    // TRANSACTIONS / TRANSFERS- -> Deposit
    @FindBy(id="deposit-menu-item")
    private WebElement depositLink;

    // TRANSACTIONS / TRANSFERS -> Withdraw
    @FindBy(id="withdraw-menu-item")
    private WebElement withdrawLink;

    /**
     * A HomePageről elnavigál a Create Savings oldalra a menüben.
     * @return a megnyitott Create Savings oldal objektuma
     */
    @Step("Navigálás a Create Savings oldalra")
    public CreateSavingsPage goToNewSavingsPage() {
        logger.info("goToNewSavingsPage() called...");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("newSavingsMenuItem.click()");
        newSavingsMenuItem.click();

        takesScreenshot();

        return new CreateSavingsPage(driver);

    }

    /**
     * HomePageről elnavigál a View Savings oldalra a menüben.
     * @return a megnyitott View Savings oldal objektuma
     */
    @Step("Navigálás a View Savings oldalra")
    public ViewSavingsAccountsPage goToViewSavingsPage() {
        logger.info("goToViewSavingsPage() called...");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click()");
        viewCheckingMenuItem.click();

        takesScreenshot();

        return new ViewSavingsAccountsPage(driver);
    }

    /**
     * HomePageről elnavigál a Deposit oldalra a menüben.
     * @return a megnyitott Deposit oldal objektuma
     */
    @Step("Navigálás a Deposit oldalra")
    public DepositPage goToDepositPage() {
        logger.info("gotoDepositPage() called...");

        logger.trace("depositLink.click()");
        depositLink.click();

        takesScreenshot();

        return new DepositPage(driver);
    }

    /**
     * HomePageről elnavigálunk a Savings/Transactions oldalra.
     * @return Savings/Transactions oldal objektuma
     */
    @Step("Navigálás a 'View Savings' oldalon található tranzakciós táblázat oldalára")
    public TransactionsPage goToTransactionsPage() {
        logger.info("goToTransactionsPage() called...");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click()");
        viewSavingsMenuItem.click();

        takesScreenshot();

        return new TransactionsPage(driver);
    }

    /**
     * HomePageről elnavigálunk a MyProfile oldalra.
     *
     * @return MyProfile oldal objektuma
     */
    @Step("Navigálás a profil adatokat megjelenítő és módosító oldalra")
    public MyProfilePage goToMyProfilePage() {
        logger.info("goToMyProfilePage() called...");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownMyProfileLink.click()");
        avatarDropdownMyProfileLink.click();

        takesScreenshot();

        return new MyProfilePage(driver);
    }

    /**
     * Adatok törlése
     */
    @Step("Adatok törlése.")
    public void deleteData() {
        logger.info("deleteData() called...");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownDeleteDataLink.click()");
        avatarDropdownDeleteDataLink.click();

        takesScreenshot();
    }

    /**
     * Ellenőrzi a HomePage betöltődését
     *
     * @return true, ha betöltődött a HomePage
     */
    @Step("HomePage betöltődésének ellenőrzése")
    public boolean isLoaded() {
        logger.info("HomePage.isLoaded() called...");
        boolean isLoaded = isLoaded(savingsMenu) && isLoaded(checkingMenu)
                && isLoaded(avatarDropdownMenuButton);
        logger.trace("isLoaded= " + isLoaded);

        takesScreenshot();

        return isLoaded;
    }

    /**
     * Logout megvalósítása
     *
     * @return LoginPage típusú objektum
     */
    @Step("Logout megvalósítása.")
    public LoginPage logout() {
        logger.info("logout() called...");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownLogoutLink.click()");
        avatarDropdownLogoutLink.click();

        takesScreenshot();

        return new LoginPage(driver);
    }

    /**
     * HomePage ellenőrzése.
     */
    @Step("HomePage ellenőrzése.")
    public void validateHomePage() {
        logger.info("validateHomePage() called...");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.REG_FIRST_NAME),
                labelTitle.getText());
    }

    /**
     * HomePage ellenőrzése profil módosítása után.
     */
    @Step("HomePage ellenőrzése profil módosítása után.")
    public void validateHomePageAfterModifyProfile() {
        logger.info("validateHomePageAfterModifyProfile() called...");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.MOD_FIRST_NAME),
                labelTitle.getText());
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
