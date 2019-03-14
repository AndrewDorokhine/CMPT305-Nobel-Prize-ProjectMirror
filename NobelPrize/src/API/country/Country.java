package API.country;

/**
 * Country entries for the list in CountryResult.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class Country {
    // Class variables.
    private String name;
    private String code;
    /**
     * Deep copy constructor.
     * @param o Country to be copied
     */
    public Country (Country o) {
        name = o.getName();
        code = o.getCode();
    }
    /**
     * Getter for the name
     * @return String
     */
    public String getName() {
        return name + "";
    }
    /**
     * Getter for the country code
     * @return String
     */
    public String getCode() {
        return code + "";
    }
    /**
     * Get the country name and code as a string for printing.
     * @return string of the country
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(name);
        builder.append("\nCode: ");
        builder.append(code);
        builder.append("\n\n");
        return builder.toString();
    }
}
