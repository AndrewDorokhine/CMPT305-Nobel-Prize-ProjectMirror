package API.prize;

import java.util.ArrayList;
import java.util.List;

/**
 * Resembles a prize from the Nobel Prize API for a certain year.
 * Has a list of laureates that won.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class Prize {
    /**
     * Class attribute variables.
     */
    private final String year;
    private final String category;
    private final List<PrizeLaureate> laureates;
    /**
     * Deep copy constructor.
     * @param o Prize to copy
     */
    public Prize (Prize o) {
        year      = o.getYear();
        category  = o.getCategory();
        laureates = o.getLaureates();
    }
    /**
     * Getter for the year.
     * @return String of the year
     */
    public String getYear() {
        return year + "";
    }
    /**
     * Getter for the category.
     * @return String of the category
     */
    public String getCategory() {
        return category + "";
    }
    /**
     * Getter for the laureates list
     * @return List of laureates
     */
    public List getLaureates() {
        List<PrizeLaureate> copy = new ArrayList();
        for (PrizeLaureate p : laureates) {
            copy.add(new PrizeLaureate(p));
        }
        return copy;
    }
    /**
     * Get the prize as a string for printing.
     * @return string representation of the prize
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Year: ");
        builder.append(getYear());
        builder.append("\n");
        builder.append("Category: ");
        builder.append(getCategory());
        builder.append("\n");
        
        for (Object p : getLaureates()) {
            builder.append(((PrizeLaureate)p).toString());
        }
        builder.append("\n");
        return builder.toString();
    }
}
