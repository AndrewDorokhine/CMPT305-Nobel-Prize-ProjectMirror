package API.country;

/**
 * Country entries for the list in CountryResult.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class Country {
    /**
     * Class attribute variables
     */
    private final String name;
    private final String code;
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
     * @return the country name
     */
    public String getName() {
        return name + "";
    }
    /**
     * Getter for the country code
     * @return the country code
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
