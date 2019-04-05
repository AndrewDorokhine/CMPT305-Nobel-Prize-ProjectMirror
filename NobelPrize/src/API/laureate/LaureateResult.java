package API.laureate;

import java.util.List;

/**
 * Database used when parsing laureate information with GSON from the Nobel
 * Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class LaureateResult {
    /**
     * Class attribute variables.
     */
    List<Laureate> laureates;
    /**
     * Class Constructor.
     */
    public LaureateResult() {}
    /**
     * Creates a string representation of the object.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // JAD ADD CODE HERE
        return builder.toString();
    }
}
