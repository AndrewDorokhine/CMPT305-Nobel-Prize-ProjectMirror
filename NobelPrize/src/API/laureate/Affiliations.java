package API.laureate;

/**
 * A laureate's affiliations.
 * 
 * @author @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class Affiliations {
    String name; 
    String city;
    String country;
    /**
     * Deep copy constructor.
     */
    public Affiliations(Affiliations o) {
        name    = o.getName();
        city    = o.getCity();
        country = o.getCountry();
    }
    /**
     * Getter for the name.
     * @return  String 
     */
    public String getName() {
        return name + "";
    }
    /**
     * Getter for the city.
     * @return String
     */
    public String getCity() {
        return city + "";
    }
    /**
     * Getter for the country.
     * @return String
     */
    public String getCountry() {
        return country + "";
    }
    /**
     * Get the affiliations as a string for printing.
     * @return string representation of the affiliations
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append("\n");
        builder.append(getCity());
        builder.append("\n");
        builder.append(getCountry());
        builder.append("\n");
        return builder.toString();
    }
}
