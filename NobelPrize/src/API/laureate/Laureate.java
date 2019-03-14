package API.laureate;

import java.util.List;

/**
 * A Nobel Prize laureate.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class Laureate {
    String id;
    String firstname;
    String surname;
    String born;
    String died;
    String bornCountry;
    String bornCountryCode;
    String bornCity;
    String diedCountry;
    String diedCountryCode;
    String diedCity;
    String gender;
    List<PrizePlus> prizes;
    /**
     * Deep copy constructor.
     * @param o Laureate to copy
     */
    public Laureate(Laureate o) {
        /**
        id              = o.getID();
        firstname       = o.getFirstName();
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
        */
    }
    /**
     * Getter for the id
     * @return String
     */
    public String getID() {
        return id + "";
    }
    // JAD ADD CODE HERE FOR GETTERS FOR ALL THE CLASS VARIABLES LIKE THE 
    // EXAMPLE ABOVE. AND ADD A TOSTRING METHOD.
}
