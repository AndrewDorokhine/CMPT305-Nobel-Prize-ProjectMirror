package API.laureate;

/**
 * A laureate's affiliations.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class Affiliations {
    /**
     * Class attribute variables
     */
    String name; 
    String city;
    String country;
    
    /**
     * Class Constructor.
     */
    public Affiliations() {
        name = "";
        city = "";
        country = "";
    }
    /**
     * Store parameter n in String name
     * @param n the name to be stored
     */
    public void addName(String n) {
        name = n;
    }
    /**
     * Store parameter c in String city
     * @param c the city to be stored
     */
    public void addCity(String c) {
        city = c;
    }
    /**
     * Store parameter c in String country
     * @param c the country to be stored
     */
    public void addCountry(String c) {
        country = c;
    }
    /**
     * Deep copy constructor.
     * @param o Affiliations to be copied
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
