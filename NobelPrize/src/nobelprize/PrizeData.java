package nobelprize;

import java.util.List;

/**
 * Database for use with the Prizes method from the Nobel Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class PrizeData {
    private List<Prize> prizes;
    
    /**
     * Get the list of all prizes as a string for printing.
     * @return string representation of the prizes list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Prize p : prizes) {
            builder.append(p.toString());
        }
        return builder.toString();
    }
    
    /**
     * FOR TESTING
     * Only get a string of the first 5 prizes for printing.
     * @return string of the first 5 entries in the prizes list
     */
    public String getHead() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < 5; i++) {
            builder.append(prizes.get(i).toString());
        }
        return builder.toString();
    }
}
