package nobelprize;

import java.util.List;

/**
 *
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class Prize {
    private String year;
    private String category;
    private List<PrizeLaureate> laureates;
    
    
    /**
     * Get the prize as a string for printing.
     * @return string representation of the prize
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Year: ");
        builder.append(year);
        builder.append("\n");
        builder.append("Category: ");
        builder.append(category);
        builder.append("\n");
        
        for (PrizeLaureate p : laureates) {
            builder.append(p.toString());
        }
        builder.append("\n");
        return builder.toString();
    }
}
