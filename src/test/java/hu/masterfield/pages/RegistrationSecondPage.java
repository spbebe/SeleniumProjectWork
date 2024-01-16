package hu.masterfield.pages;

import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Regisztráciős form második oldalának osztálya
 *
 */
@Feature("Regisztráció - 2. oldal")
public class RegistrationSecondPage extends BasePage{
    // Az oldalon találhatón webelementek azonosítása, amelyekre szükségünk van.

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


    public RegistrationSecondPage(WebDriver driver) {
        super(driver);
    }
}
