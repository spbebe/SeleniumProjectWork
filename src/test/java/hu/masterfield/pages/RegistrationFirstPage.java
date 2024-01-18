package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Regisztráciős form első oldalának osztálya
 *
 */

@Feature("Regisztráció - 1. oldal")
public class RegistrationFirstPage extends BasePage{

    // Az oldalon található webelementek azonosítása, amelyekre szükségünk van.

    // megszólítás megadása
    @FindBy(id="title")
    private WebElement titleSelect;

    // keresztnév megadása
    @FindBy(id="firstName")
    private WebElement firstnameInput;

    // vezetéknév megadása
    @FindBy(id="lastName")
    private WebElement lastnameInput;

    // férfi nem kiválasztása
    @FindBy(xpath="//input[@type='radio' and @name='gender' and @value='M']")
    private WebElement genderMaleRadio;

    // női nem kiválsztása
    @FindBy(css="input[type='radio'][name='gender'][value='F']")
    private WebElement genderFemaleRadio;

    // születési dátum
    @FindBy(id="dob")
    private WebElement dateOfBirthInput;

    // tb szám
    @FindBy(id="ssn")
    private WebElement socialSecurityNumberInput;

    // email cím
    @FindBy(id="emailAddress")
    private WebElement emailAddressInput;

    // jelszó megadása
    @FindBy(id="password")
    private WebElement regPasswordInput;

    // jelszó megerősítése
    @FindBy(id="confirmPassword")
    private WebElement regConfirmPasswordInput;

    // tovább gomb
    @FindBy(xpath="//button[@type='submit' and text()='Next']")
    private WebElement nextPageButton;

    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }

    /** Ellenőrizzük, hogy megjelentek-e az oldalon az adott elemek.
     *
     * @return true , ha az oldal betöltődött, megjelentek az elvárt elemek és
     * kattinthatóak
     */
    @Step("Regisztrációs űrlap 1. oldalának betöltésének ellenőrzése")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect)
                && isLoaded(firstnameInput)
                && isLoaded(lastnameInput)
                && isLoaded(genderMaleRadio)
                && isLoaded(genderFemaleRadio)
                && isLoaded((dateOfBirthInput))
                && isLoaded(socialSecurityNumberInput)
                && isLoaded(emailAddressInput)
                && isLoaded(regPasswordInput)
                && isLoaded(regConfirmPasswordInput)
                && isLoaded(nextPageButton);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    /**
     * Példányosítjuk a RegistrationData osztályt, hogy az oldalon található input mezőket
     * ki tudjuk tölteni a globalTestData.properties fileból felolvasott tesztadatokkal.
     *
     */

    RegistrationData registrationData = new RegistrationData();

    /**
     * Regisztráció első oldalát valósítjuk meg.
     */
    @Step("Regisztrációs úrlap 1. oldalának kitöltése")
    public RegistrationSecondPage registrationFirstPage() {     //visszatérési értéke secondPage lesz, a metódus neve firstPage
        logger.info("registrationFirstPage() called.");

        logger.trace("titleSelect.select");
        Select selectTitle = new Select(titleSelect);
        selectTitle.selectByVisibleText(registrationData.getTitle());

        setTextbox(firstnameInput, "firstnameInput"
                , registrationData.getFirstName());

        setTextbox(lastnameInput, "lastnameInput"
                , registrationData.getLastName());

        if (registrationData.getGender().equals("M")) {
            if (genderMaleRadio.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderMaleRadio.click() called.");
                genderMaleRadio.click();
            }
        }
        if (registrationData.getGender().equals("F")) {
            if (genderFemaleRadio.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderFemaleRadio.click() called.");
                genderFemaleRadio.click();
            }
        }

        setTextbox(dateOfBirthInput, "dateOfBirthInput"
                ,registrationData.getDateOfBirth());

        setTextbox(socialSecurityNumberInput, "socialSecurityNumberInput",
                registrationData.getSocialSecurityNumber());

        setTextbox(emailAddressInput, "emailAddressInput",
                registrationData.getEmailAddress());

        setTextbox(regPasswordInput, "regPasswordInput",
                registrationData.getPassword());

        setTextbox(regConfirmPasswordInput, "regConfirmPasswordInput",
                registrationData.getConfirmPassword());

        takesScreenshot();

        logger.trace("nextPageButton.click() called.");
        nextPageButton.click();

        return new RegistrationSecondPage(driver);
    }
}