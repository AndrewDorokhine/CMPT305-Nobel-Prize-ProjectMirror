package nobelprize;

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
