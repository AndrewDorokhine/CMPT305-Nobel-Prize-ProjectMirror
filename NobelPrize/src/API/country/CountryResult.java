package API.country;

import java.util.ArrayList;
import java.util.List;

/**
 * Database for use with the Countries method from the Nobel Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class CountryResult {
    // Class variables
    private List<Country> countries;
    /**
     * Gets a deep copy of the countries list.
     * @return List of country objects
     */
    public List<Country> getCountries() {
        ArrayList copy = new ArrayList();
        for (Country c : countries) {
            copy.add(new Country(c));
        }
        return copy;
    }
    /**
     * Get the list of all countries as a string for printing.
     * @return string representation of the countries list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Country c : getCountries()) {
            builder.append(c.toString());
        }
        return builder.toString();
    }
}
