package nobelprize;

import java.util.List;

/**
 * Database for use with the Countries method from the Nobel Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class CountryData {
    // Class variables.
    private List<Country> countries;
    
    
    /**
     * Get the list of all countries as a string for printing.
     * @return string representation of the countries list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Country c : countries) {
            builder.append(c.toString());
        }
        return builder.toString();
    }
    
    /**
     * FOR TESTING
     * Only get a string of the first 5 countries for printing.
     * @return string of the first 5 entries in the countries list
     */
    public String getHead() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < 5; i++) {
            builder.append(countries.get(i).toString());
        }
        return builder.toString();
    }
}
