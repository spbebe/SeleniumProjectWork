package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Regisztráciős form második oldalának osztálya
 *
 */
@Feature("Regisztráció - 2. oldal")
public class RegistrationSecondPage extends BasePage{
    // Az oldalon találhatón webelementek azonosítása, amelyekre szükségünk van.

    protected static Logger logger = LogManager.getLogger(RegistrationSecondPage.class);
    @FindBy(id="address")
    private WebElement addressInput;        // cím megadása

    @FindBy(id="locality")
    private WebElement localityInput;       // település megadása

    @FindBy(id="region")
    private WebElement regionInput;         // régió megadása

    @FindBy(id="postalCode")
    private WebElement postalCodeInput;     // irányítószám megadása

    @FindBy(id="country")
    private WebElement countryInput;        // ország megadása

    @FindBy(id="homePhone")
    private WebElement homePhoneInput;      // otthoni telefonszám megadása

    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneInput;    // mobilszám megadása

    @FindBy(id="workPhone")
    private WebElement workPhoneInput;      // munkahelyi telefonszám megadása

    @FindBy(id="agree-terms")
    private WebElement agreeTermsCheckbox;  //feltételek elfogadása

    //@FindBy(css="button[type='submit']")
    @FindBy(xpath="//button[@type='submit' and text()='Register']")
    private WebElement registerButton;    // regisztráció gomb


    public RegistrationSecondPage(WebDriver driver) {

        super(driver);
    }

    /**
     * Ellenőrzi, hogy megjelentek-e az oldalon a megadott elemek
     *
     * @return true, ha az oldal betöltődött, megjelentek az elemek és kattinthatóak
     */

    @Step("Regisztrációs űrlap 2. oldalának betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(addressInput)
                && isLoaded(localityInput)
                && isLoaded(regionInput)
                && isLoaded(postalCodeInput)
                && isLoaded(countryInput)
                && isLoaded(homePhoneInput)
                && isLoaded(mobilePhoneInput)
                && isLoaded(workPhoneInput)
                && isLoaded(agreeTermsCheckbox)
                && isLoaded(registerButton);

        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }

    /**
     * Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket
     * a GlobalTestData.properties fileban megadott adatokkal tudjuk kitölteni
     * Így a regisztráció 2. oldalának kitöltésekor nem kell felsorolni a sok bemenő paramétert
     */
    RegistrationData registrationData = new RegistrationData();

    /**
     * A regisztráció második oldalának kitöltését valósítjuk meg.
     */

    @Step("A regisztráció második oldalának kitöltése.")
    public LoginPage registrationSecondPage() {
        logger.info("registrationSecondPage() called.");

        setTextbox(addressInput, "addressInput", registrationData.getAddress());
        setTextbox(localityInput, "localityInput", registrationData.getLocality());
        setTextbox(regionInput, "regionInput", registrationData.getRegion());
        setTextbox(postalCodeInput, "postalCodeInput", registrationData.getPostalCode());
        setTextbox(countryInput, "countryInput", registrationData.getCountry());
        setTextbox(homePhoneInput, "homePhoneInput", registrationData.getHomePhone());
        setTextbox(mobilePhoneInput, "mobilePhoneInput", registrationData.getMobilePhone());
        setTextbox(workPhoneInput, "workPhoneInput", registrationData.getWorkPhone());

        logger.trace("agreeTermsCheckbox.click()");
        if (agreeTermsCheckbox.isSelected()) {
            // TO DO NOTHING!!
        } else {
            agreeTermsCheckbox.click();
        }

        takesScreenshot();

        logger.trace("registerButton.click()");
        registerButton.click();

        return new LoginPage(driver);
    }
}
