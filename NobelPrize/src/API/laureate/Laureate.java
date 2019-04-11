package API.laureate;

import java.util.ArrayList;
import java.util.List;

/**
 * A Nobel Prize laureate.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class Laureate {
    /**
     * Class attribute variables.
     */
    private final String id;
    private final String firstname;
    private final String surname;
    private final String born;
    private final String died;
    private final String bornCountry;
    private final String bornCountryCode;
    private final String bornCity;
    private final String diedCountry;
    private final String diedCountryCode;
    private final String diedCity;
    private final String gender;
    private final List<PrizePlus> prizes;
    /**
     * Deep copy constructor.
     * @param o Laureate to copy
     */
    public Laureate(Laureate o) {
        id              = o.getID();
        firstname       = o.getFirstname();
        surname         = o.getSurname();
        born            = o.getBorn();
        died            = o.getDied();
        bornCountry     = o.getBornCountry();
        bornCountryCode = o.getBornCountryCode();
        bornCity        = o.getBornCity();
        diedCountry     = o.getDiedCountry();
        diedCountryCode = o.getDiedCountryCode();
        diedCity        = o.getDiedCity();
        gender          = o.getGender();
        prizes          = o.getPrizes();
    }
    /**
     * Checks if the laureate was either born in or died in a country.
     * @param c the country in question
     * @return boolean of whether the laureate was born or died in the country
     */
    public boolean affiliatedWithCountry(String c) {
        boolean result;
        result = c.equals(getBornCountry());
        result = c.equals(getDiedCountry());
        return result;
    }
    /**
     * Getter for the id
     * @return String
     */
    public String getID() {
        return id + "";
    }
    /**
     * Getter for the firstname
     * @return String
     */
    public String getFirstname() {
        return firstname + "";
    }
    /**
     * Getter for the surname
     * @return String
     */
    public String getSurname() {
        return surname + "";
    }
    /**
     * Getter for born
     * @return String
     */
    public String getBorn() {
        return born + "";
    }
    /**
     * Getter for died
     * @return String
     */
    public String getDied() {
        return died + "";
    }
    /**
     * Getter for bornCountry
     * @return String
     */
    public String getBornCountry() {
        return bornCountry + "";
    }
    /**
     * Getter for the bornCountryCode
     * @return String
     */
    public String getBornCountryCode() {
        return bornCountryCode + "";
    }
    /**
     * Getter for the bornCity
     * @return String
     */
    public String getBornCity() {
        return bornCity + "";
    }
    /**
     * Getter for the diedCountry
     * @return String
     */
    public String getDiedCountry() {
        return diedCountry + "";
    }
    /**
     * Getter for the diedCountryCode
     * @return String
     */
    public String getDiedCountryCode() {
        return diedCountryCode + "";
    }
    /**
     * Getter for the diedCity
     * @return String
     */
    public String getDiedCity() {
        return diedCity + "";
    }
    /**
     * Getter for the gender
     * @return String
     */
    public String getGender() {
        return gender + "";
    }
    /**
     * Getter for prizes
     * @return String
     */
    public List<PrizePlus> getPrizes() {
        List copy = new ArrayList();
        for (PrizePlus p : prizes) {
            copy.add(new PrizePlus(p));
        }
        return copy;
    }
    /**
     * Creates a string representation of the object.
     * @return string of the object's fields
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ");
        builder.append(id);
        builder.append("\nFirstname: ");
        builder.append(firstname);
        builder.append("\nSurname: ");
        builder.append(surname);
        builder.append("\nBorn: ");
        builder.append(born);
        builder.append("\nDied: ");
        builder.append(died);
        builder.append("\nBornCountry: ");
        builder.append(bornCountry);
        builder.append("\nBornCountryCode: ");
        builder.append(bornCountryCode);
        builder.append("\nBornCity: ");
        builder.append(bornCity);
        builder.append("\nDiedCountry: ");
        builder.append(diedCountry);
        builder.append("\nDiedCountryCode: ");
        builder.append(diedCountryCode);
        builder.append("\nDiedCity: ");
        builder.append(diedCity);
        builder.append("\nGender: ");
        builder.append(gender);
        builder.append("\nPrizes: ");
        builder.append(prizes);
        builder.append("\n\n");
        return builder.toString();
    }
}
