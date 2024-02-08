package hu.masterfield.datatypes;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class ModifyData {
    /**
     *  Hozzáférés biztosítása a globalTestData.properties filehoz.
     */

    protected static GlobalTestData globalTestData = new GlobalTestData();

    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String locality;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    /** Paraméter nélküli konstruktor, amivel a globalTestData.properties fileból
     * hozzáférünk a tesztadatokhoz.
     *
     * A példányosításnál nem kell mindenhol felsorolni az összes adatot.
     *
     */

    public ModifyData() {
        this.title = globalTestData.getProperty(Consts.MOD_TITLE);
        this.firstName = globalTestData.getProperty(Consts.MOD_FIRST_NAME);
        this.lastName = globalTestData.getProperty(Consts.MOD_LAST_NAME);
        this.address = globalTestData.getProperty(Consts.MOD_ADDRESS);
        this.gender = globalTestData.getProperty(Consts.REG_GENDER);
        this.locality = globalTestData.getProperty(Consts.MOD_LOCALITY);
        this.region = globalTestData.getProperty(Consts.MOD_REGION);
        this.postalCode = globalTestData.getProperty(Consts.MOD_POSTAL_CODE);
        this.country = globalTestData.getProperty(Consts.MOD_COUNTRY);
        this.homePhone = globalTestData.getProperty(Consts.MOD_HOME_PHONE);
        this.mobilePhone = globalTestData.getProperty(Consts.MOD_MOBILE_PHONE);
        this.workPhone = globalTestData.getProperty(Consts.MOD_WORK_PHONE);
    }
}
