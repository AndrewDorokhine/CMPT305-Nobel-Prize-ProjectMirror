package API.laureate;

import java.util.ArrayList;
import java.util.List;

/**
 * Database used when parsing laureate information with GSON from the Nobel
 * Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class LaureateResult {
    List<Laureate> laureates;
    /**
     * Constructor.
     */
    public LaureateResult() {}
    /**
     * Getter for the laureates list.
     * @return List copy of the laureates list
     */
    public List getLaureates() {
        List<Laureate> copy = new ArrayList();
        // JAD ADD CODE HERE
        return copy;
    }
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
